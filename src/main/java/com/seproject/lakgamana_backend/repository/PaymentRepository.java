package com.seproject.lakgamana_backend.repository;

import com.seproject.lakgamana_backend.entity.Payment;
import com.seproject.lakgamana_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByBooking(Booking booking);
    List<Payment> findByBookingBookingId(Integer bookingId);
    
    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = ?1")
    List<Payment> findByPaymentStatus(Payment.PaymentStatus paymentStatus);
}