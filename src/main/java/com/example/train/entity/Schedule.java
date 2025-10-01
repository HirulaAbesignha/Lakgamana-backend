package com.example.train.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="schedule")
public class Schedule {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="train_id")
    private Train train;
    @ManyToOne(optional=false) @JoinColumn(name="route_id")
    private Route route;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats;
    private Double price;
    public Long getId(){ return id; } public void setId(Long id){ this.id=id; }
    public Train getTrain(){ return train; } public void setTrain(Train train){ this.train=train; }
    public Route getRoute(){ return route; } public void setRoute(Route route){ this.route=route; }
    public LocalDateTime getDepartureTime(){ return departureTime; } public void setDepartureTime(LocalDateTime departureTime){ this.departureTime=departureTime; }
    public LocalDateTime getArrivalTime(){ return arrivalTime; } public void setArrivalTime(LocalDateTime arrivalTime){ this.arrivalTime=arrivalTime; }
    public Integer getAvailableSeats(){ return availableSeats; } public void setAvailableSeats(Integer availableSeats){ this.availableSeats=availableSeats; }
    public Double getPrice(){ return price; } public void setPrice(Double price){ this.price=price; }
}