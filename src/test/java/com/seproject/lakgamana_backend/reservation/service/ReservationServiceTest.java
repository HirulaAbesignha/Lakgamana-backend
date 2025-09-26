package com.seproject.lakgamana_backend.reservation.service;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seproject.lakgamana_backend.entity.Payment;
import com.seproject.lakgamana_backend.reservation.dto.ReservationRequest;
import com.seproject.lakgamana_backend.reservation.entity.Reservation;
import com.seproject.lakgamana_backend.reservation.entity.SeatAvailability;
import com.seproject.lakgamana_backend.reservation.repository.ReservationRepository;
import com.seproject.lakgamana_backend.reservation.repository.SeatAvailabilityRepository;
import com.seproject.lakgamana_backend.service.PaymentService;
import com.seproject.lakgamana_backend.service.RouteService;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private SeatAvailabilityRepository seatAvailabilityRepository;
    @Mock
    private RouteService routeService;
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testCreateReservationSuccess() {
        ReservationRequest request = new ReservationRequest();
        request.setTrainId(1L);
        request.setRouteId(1L);
        request.setSeatNumber("A12");
        request.setClassType(Reservation.ClassType.FIRST);
        request.setTravelDate(LocalDate.of(2025, 10, 1));
        request.setAmount(50.0);

        SeatAvailability seat = new SeatAvailability();
        seat.setAvailable(true);
        when(seatAvailabilityRepository.findByTrainIdAndTravelDateAndSeatNumber(1L, LocalDate.of(2025, 10, 1), "A12"))
                .thenReturn(Optional.of(seat));
        when(routeService.isValidTrainAndRoute(1L, 1L)).thenReturn(true);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(i -> i.getArguments()[0]);
        when(paymentService.initiatePayment(anyLong(), eq(50.0))).thenReturn(new Payment(1L));

        Reservation reservation = reservationService.createReservation(request, 1L);

        assertNotNull(reservation);
        assertEquals(Reservation.ReservationStatus.CONFIRMED, reservation.getStatus());
        verify(seatAvailabilityRepository).save(seat);
        verify(reservationRepository, times(2)).save(any(Reservation.class));
    }
}


