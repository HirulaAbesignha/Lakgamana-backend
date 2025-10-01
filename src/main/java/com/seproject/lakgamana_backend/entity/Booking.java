package com.seproject.lakgamana_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Passenger passenger;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Train train;
    
    @NotNull
    @Column(name = "journey_date", nullable = false)
    private LocalDate journeyDate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;
    
    @Size(max = 10)
    @Column(name = "seat_num", length = 10)
    private String seatNum;
    
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;
    
    public enum ClassType {
        FIRST("1st"),
        SECOND("2nd"),
        THIRD("3rd");
        
        private final String value;
        
        ClassType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    public enum BookingStatus {
        confirmed, pending, cancelled
    }
    
    // Default constructor
    public Booking() {
        this.bookingDate = LocalDateTime.now();
        this.status = BookingStatus.pending;
    }
    
    // Constructor with parameters
    public Booking(Passenger passenger, Train train, LocalDate journeyDate, ClassType classType, String seatNum) {
        this.passenger = passenger;
        this.train = train;
        this.journeyDate = journeyDate;
        this.classType = classType;
        this.seatNum = seatNum;
        this.bookingDate = LocalDateTime.now();
        this.status = BookingStatus.pending;
    }
    
    // Getters and Setters
    public Integer getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    
    public Passenger getPassenger() {
        return passenger;
    }
    
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public Train getTrain() {
        return train;
    }
    
    public void setTrain(Train train) {
        this.train = train;
    }
    
    public LocalDate getJourneyDate() {
        return journeyDate;
    }
    
    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }
    
    public ClassType getClassType() {
        return classType;
    }
    
    public void setClassType(ClassType classType) {
        this.classType = classType;
    }
    
    public String getSeatNum() {
        return seatNum;
    }
    
    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }
    
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public BookingStatus getStatus() {
        return status;
    }
    
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}