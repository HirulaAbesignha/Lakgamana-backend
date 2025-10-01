package com.example.train.dto;
import jakarta.validation.constraints.NotBlank; import jakarta.validation.constraints.NotNull;
public class CreateRouteDTO {
    @NotBlank private String startStation;
    @NotBlank private String endStation;
    @NotNull private Double distanceKm;
    public String getStartStation(){ return startStation; } public void setStartStation(String startStation){ this.startStation=startStation; }
    public String getEndStation(){ return endStation; } public void setEndStation(String endStation){ this.endStation=endStation; }
    public Double getDistanceKm(){ return distanceKm; } public void setDistanceKm(Double distanceKm){ this.distanceKm=distanceKm; }
}