package com.seproject.lakgamana_backend.repository;

import com.seproject.lakgamana_backend.entity.Booking;
import com.seproject.lakgamana_backend.entity.Passenger;
import com.seproject.lakgamana_backend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByPassenger(Passenger passenger);
    List<Booking> findByTrain(Train train);
    List<Booking> findByPassengerPassengerId(Integer passengerId);
    List<Booking> findByTrainTrainId(Integer trainId);
    List<Booking> findByJourneyDate(LocalDate journeyDate);
    
    @Query("SELECT b FROM Booking b WHERE b.passenger.passengerId = ?1 AND b.status = ?2")
    List<Booking> findByPassengerIdAndStatus(Integer passengerId, Booking.BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.train.trainId = ?1 AND b.journeyDate = ?2")
    List<Booking> findByTrainIdAndJourneyDate(Integer trainId, LocalDate journeyDate);
}