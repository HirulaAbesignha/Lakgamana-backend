package com.seproject.lakgamana_backend.reservation.entity;

import com.seproject.lakgamana_backend.entity.Train;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "seat_availability")
public class SeatAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private Reservation.ClassType classType;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Version
    private Long version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public Reservation.ClassType getClassType() { return classType; }
    public void setClassType(Reservation.ClassType classType) { this.classType = classType; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}