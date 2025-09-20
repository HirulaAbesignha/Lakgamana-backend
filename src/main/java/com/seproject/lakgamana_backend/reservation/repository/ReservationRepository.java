package com.seproject.lakgamana_backend.reservation.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seproject.lakgamana_backend.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByStatusAndTravelDate(Reservation.ReservationStatus status, LocalDate travelDate);
    Optional<Reservation> findByIdAndUserId(Long id, Long userId);
}
