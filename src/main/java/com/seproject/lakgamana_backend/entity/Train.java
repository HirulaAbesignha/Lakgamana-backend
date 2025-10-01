package com.seproject.lakgamana_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Train")
public class Train {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Integer trainId;
    
    @NotNull
    @Size(max = 20)
    @Column(name = "train_number", nullable = false, unique = true, length = 20)
    private String trainNumber;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "train_name", nullable = false, length = 100)
    private String trainName;
    
    @NotNull
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
    
    @NotNull
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;
    
    // Default constructor
    public Train() {}
    
    // Constructor with parameters
    public Train(String trainNumber, String trainName, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    // Getters and Setters
    public Integer getTrainId() {
        return trainId;
    }
    
    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }
    
    public String getTrainNumber() {
        return trainNumber;
    }
    
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
    
    public String getTrainName() {
        return trainName;
    }
    
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}