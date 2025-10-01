package com.seproject.lakgamana_backend.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.seproject.lakgamana_backend.reservation.entity.Reservation;

public class ReservationResponse {
    private Long id;
    private Long userId;
    private Long trainId;
    private Long routeId;
    private String seatNumber;
    private Reservation.ClassType classType;
    private LocalDate travelDate;
    private Reservation.ReservationStatus status;
    private Long paymentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.userId = reservation.getUser().getId();
        this.trainId = reservation.getTrain().getId();
        this.routeId = reservation.getRoute().getId();
        this.seatNumber = reservation.getSeatNumber();
        this.classType = reservation.getClassType();
        this.travelDate = reservation.getTravelDate();
        this.status = reservation.getStatus();
        this.paymentId = reservation.getPayment() != null ? reservation.getPayment().getId() : null;
        this.createdAt = reservation.getCreatedAt();
        this.updatedAt = reservation.getUpdatedAt();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTrainId() { return trainId; }
    public void setTrainId(Long trainId) { this.trainId = trainId; }
    public Long getRouteId() { return routeId; }
    public void setRouteId(Long routeId) { this.routeId = routeId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public Reservation.ClassType getClassType() { return classType; }
    public void setClassType(Reservation.ClassType classType) { this.classType = classType; }
    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    public Reservation.ReservationStatus getStatus() { return status; }
    public void setStatus(Reservation.ReservationStatus status) { this.status = status; }
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
