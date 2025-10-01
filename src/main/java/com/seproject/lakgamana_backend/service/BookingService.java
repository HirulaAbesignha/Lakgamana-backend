package com.seproject.lakgamana_backend.service;

import com.seproject.lakgamana_backend.entity.Booking;
import com.seproject.lakgamana_backend.entity.Passenger;
import com.seproject.lakgamana_backend.entity.Train;
import com.seproject.lakgamana_backend.repository.BookingRepository;
import com.seproject.lakgamana_backend.repository.PassengerRepository;
import com.seproject.lakgamana_backend.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private PassengerRepository passengerRepository;
    
    @Autowired
    private TrainRepository trainRepository;
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }
    
    public List<Booking> getBookingsByPassengerId(Integer passengerId) {
        return bookingRepository.findByPassengerPassengerId(passengerId);
    }
    
    public List<Booking> getBookingsByTrainId(Integer trainId) {
        return bookingRepository.findByTrainTrainId(trainId);
    }
    
    public List<Booking> getBookingsByPassengerIdAndStatus(Integer passengerId, Booking.BookingStatus status) {
        return bookingRepository.findByPassengerIdAndStatus(passengerId, status);
    }
    
    public Booking createBooking(Integer passengerId, Integer trainId, LocalDate journeyDate, 
                               Booking.ClassType classType, String seatNum) {
        
        // Validate passenger exists
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + passengerId));
        
        // Validate train exists
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + trainId));
        
        // Validate journey date is not in the past
        if (journeyDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Journey date cannot be in the past");
        }
        
        // Check if seat is already booked for this train and date
        List<Booking> existingBookings = bookingRepository.findByTrainIdAndJourneyDate(trainId, journeyDate);
        boolean seatTaken = existingBookings.stream()
                .anyMatch(booking -> booking.getSeatNum() != null && 
                         booking.getSeatNum().equals(seatNum) && 
                         booking.getStatus() != Booking.BookingStatus.cancelled);
        
        if (seatTaken) {
            throw new RuntimeException("Seat " + seatNum + " is already booked for this train on " + journeyDate);
        }
        
        Booking booking = new Booking(passenger, train, journeyDate, classType, seatNum);
        return bookingRepository.save(booking);
    }
    
    public Booking confirmBooking(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    booking.setStatus(Booking.BookingStatus.confirmed);
                    return bookingRepository.save(booking);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
    }
    
    public Booking rescheduleBooking(Integer bookingId, Integer newTrainId, LocalDate newJourneyDate) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        
        if (booking.getStatus() == Booking.BookingStatus.cancelled) {
            throw new RuntimeException("Cannot reschedule a cancelled booking");
        }
        
        // Validate new train exists
        Train newTrain = trainRepository.findById(newTrainId)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + newTrainId));
        
        // Validate new journey date is not in the past
        if (newJourneyDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("New journey date cannot be in the past");
        }
        
        // Check if seat is available on new train and date
        if (booking.getSeatNum() != null) {
            List<Booking> existingBookings = bookingRepository.findByTrainIdAndJourneyDate(newTrainId, newJourneyDate);
            boolean seatTaken = existingBookings.stream()
                    .anyMatch(existingBooking -> existingBooking.getSeatNum() != null && 
                             existingBooking.getSeatNum().equals(booking.getSeatNum()) && 
                             existingBooking.getStatus() != Booking.BookingStatus.cancelled &&
                             !existingBooking.getBookingId().equals(bookingId));
            
            if (seatTaken) {
                throw new RuntimeException("Seat " + booking.getSeatNum() + " is already booked for the new train on " + newJourneyDate);
            }
        }
        
        booking.setTrain(newTrain);
        booking.setJourneyDate(newJourneyDate);
        booking.setStatus(Booking.BookingStatus.pending); // Reset to pending for rescheduled booking
        
        return bookingRepository.save(booking);
    }
    
    public Booking cancelBooking(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    booking.setStatus(Booking.BookingStatus.cancelled);
                    return bookingRepository.save(booking);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
    }
    
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}