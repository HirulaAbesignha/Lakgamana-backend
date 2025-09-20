package com.seproject.lakgamana_backend.reservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seproject.lakgamana_backend.entity.Payment;
import com.seproject.lakgamana_backend.entity.Route;
import com.seproject.lakgamana_backend.entity.Train;
import com.seproject.lakgamana_backend.entity.User;
import com.seproject.lakgamana_backend.reservation.dto.ReservationRequest;
import com.seproject.lakgamana_backend.reservation.entity.Reservation;
import com.seproject.lakgamana_backend.reservation.entity.SeatAvailability;
import com.seproject.lakgamana_backend.reservation.repository.ReservationRepository;
import com.seproject.lakgamana_backend.reservation.repository.SeatAvailabilityRepository;
import com.seproject.lakgamana_backend.service.PaymentService;
import com.seproject.lakgamana_backend.service.RouteService;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SeatAvailabilityRepository seatAvailabilityRepository;
    @Autowired
    private RouteService routeService;
    @Autowired
    private PaymentService paymentService;

    @Transactional
    public Reservation createReservation(ReservationRequest request, Long userId) {
        // Validate train and route
        if (!routeService.isValidTrainAndRoute(request.getTrainId(), request.getRouteId())) {
            throw new IllegalArgumentException("Invalid train or route");
        }

        // Check seat availability
        SeatAvailability seat = seatAvailabilityRepository
                .findByTrainIdAndTravelDateAndSeatNumber(
                        request.getTrainId(), request.getTravelDate(), request.getSeatNumber())
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (!seat.isAvailable()) {
            throw new RuntimeException("Seat " + request.getSeatNumber() + " is not available");
        }

        // Create reservation
        Reservation reservation = new Reservation();
        reservation.setUser(new User(userId));
        reservation.setTrain(new Train(request.getTrainId()));
        reservation.setRoute(new Route(request.getRouteId()));
        reservation.setSeatNumber(request.getSeatNumber());
        reservation.setClassType(request.getClassType());
        reservation.setTravelDate(request.getTravelDate());
        reservation.setStatus(Reservation.ReservationStatus.PENDING);
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        // Mark seat as unavailable
        seat.setAvailable(false);
        seatAvailabilityRepository.save(seat);

        // Save reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Initiate payment
        Payment payment = paymentService.initiatePayment(savedReservation.getId(), request.getAmount());
        reservation.setPayment(payment);
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Transactional
    public void cancelReservation(Long reservationId, Long userId) {
        Reservation reservation = reservationRepository.findByIdAndUserId(reservationId, userId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (reservation.getStatus() != Reservation.ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Cannot cancel reservation in " + reservation.getStatus() + " status");
        }

        // Release seat
        SeatAvailability seat = seatAvailabilityRepository
                .findByTrainIdAndTravelDateAndSeatNumber(
                        reservation.getTrain().getId(), reservation.getTravelDate(), reservation.getSeatNumber())
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setAvailable(true);
        seatAvailabilityRepository.save(seat);

        // Update reservation status
        reservation.setStatus(Reservation.ReservationStatus.CANCELED);
        reservation.setUpdatedAt(LocalDateTime.now());
        reservationRepository.save(reservation);

        // Trigger refund
        paymentService.processRefund(reservation.getPayment().getId());
    }

    public List<SeatAvailability> getAvailableSeats(Long trainId, LocalDate travelDate) {
        // First, ensure the train exists
        Train train = new Train(trainId);
        // Try to find seats directly by train_id column
        return seatAvailabilityRepository.findByTrainIdAndTravelDateAndIsAvailableTrue(trainId, travelDate);
    }
}