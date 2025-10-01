package com.seproject.lakgamana_backend.reservation.dto;

import java.time.LocalDate;

import com.seproject.lakgamana_backend.reservation.entity.Reservation;

public class ReservationRequest {
    private Long trainId;
    private Long routeId;
    private String seatNumber;
    private Reservation.ClassType classType;
    private LocalDate travelDate;
    private Double amount;

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
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
