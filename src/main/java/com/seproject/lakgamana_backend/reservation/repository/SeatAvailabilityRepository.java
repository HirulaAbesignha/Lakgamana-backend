package com.seproject.lakgamana_backend.reservation.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seproject.lakgamana_backend.reservation.entity.SeatAvailability;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, Long> {
    @Query(value = "SELECT * FROM seat_availability WHERE train_id = :trainId AND travel_date = :travelDate AND is_available = 1", nativeQuery = true)
    List<SeatAvailability> findByTrainIdAndTravelDateAndIsAvailableTrue(@Param("trainId") Long trainId, @Param("travelDate") LocalDate travelDate);
    
    @Query(value = "SELECT * FROM seat_availability WHERE train_id = :trainId AND travel_date = :travelDate AND seat_number = :seatNumber", nativeQuery = true)
    Optional<SeatAvailability> findByTrainIdAndTravelDateAndSeatNumber(@Param("trainId") Long trainId, @Param("travelDate") LocalDate travelDate, @Param("seatNumber") String seatNumber);
}
