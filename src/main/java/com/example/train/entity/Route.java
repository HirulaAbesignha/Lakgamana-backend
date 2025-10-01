package com.example.train.entity;
import jakarta.persistence.*;
@Entity @Table(name="route")
public class Route {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String startStation;
    private String endStation;
    private Double distanceKm;
    public Long getId(){ return id; } public void setId(Long id){ this.id=id; }
    public String getStartStation(){ return startStation; } public void setStartStation(String startStation){ this.startStation=startStation; }
    public String getEndStation(){ return endStation; } public void setEndStation(String endStation){ this.endStation=endStation; }
    public Double getDistanceKm(){ return distanceKm; } public void setDistanceKm(Double distanceKm){ this.distanceKm=distanceKm; }
}