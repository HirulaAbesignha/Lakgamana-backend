package com.example.train.dto;
import jakarta.validation.constraints.NotNull; import java.time.LocalDateTime;
public class CreateScheduleDTO {
    @NotNull private Long trainId; @NotNull private Long routeId;
    @NotNull private LocalDateTime departureTime; @NotNull private LocalDateTime arrivalTime;
    private Integer availableSeats; private Double price;
    public Long getTrainId(){ return trainId; } public void setTrainId(Long trainId){ this.trainId=trainId; }
    public Long getRouteId(){ return routeId; } public void setRouteId(Long routeId){ this.routeId=routeId; }
    public LocalDateTime getDepartureTime(){ return departureTime; } public void setDepartureTime(LocalDateTime departureTime){ this.departureTime=departureTime; }
    public LocalDateTime getArrivalTime(){ return arrivalTime; } public void setArrivalTime(LocalDateTime arrivalTime){ this.arrivalTime=arrivalTime; }
    public Integer getAvailableSeats(){ return availableSeats; } public void setAvailableSeats(Integer availableSeats){ this.availableSeats=availableSeats; }
    public Double getPrice(){ return price; } public void setPrice(Double price){ this.price=price; }
}