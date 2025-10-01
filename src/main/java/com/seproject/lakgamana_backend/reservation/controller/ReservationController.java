package com.seproject.lakgamana_backend.reservation.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seproject.lakgamana_backend.reservation.dto.ReservationRequest;
import com.seproject.lakgamana_backend.reservation.dto.ReservationResponse;
import com.seproject.lakgamana_backend.reservation.entity.Reservation;
import com.seproject.lakgamana_backend.reservation.entity.SeatAvailability;
import com.seproject.lakgamana_backend.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody ReservationRequest request,
            @RequestHeader("X-User-Id") Long userId) {
        Reservation reservation = reservationService.createReservation(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReservationResponse(reservation));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponse>> getUserReservations(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        List<ReservationResponse> responses = reservations.stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable Long reservationId,
            @RequestHeader("X-User-Id") Long userId) {
        reservationService.cancelReservation(reservationId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/availability")
    public ResponseEntity<List<SeatAvailability>> checkAvailability(
            @RequestParam Long trainId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate) {
        List<SeatAvailability> seats = reservationService.getAvailableSeats(trainId, travelDate);
        return ResponseEntity.ok(seats);
    }
}
