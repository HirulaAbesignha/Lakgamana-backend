package com.seproject.lakgamana_backend.service;

import com.seproject.lakgamana_backend.entity.Payment;
import com.seproject.lakgamana_backend.entity.Booking;
import com.seproject.lakgamana_backend.repository.PaymentRepository;
import com.seproject.lakgamana_backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }
    
    public Optional<Payment> getPaymentByBookingId(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        return paymentRepository.findByBooking(booking);
    }
    
    public Payment createPayment(Integer bookingId, BigDecimal amount, Payment.PaymentMethod paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        
        // Check if payment already exists for this booking
        Optional<Payment> existingPayment = paymentRepository.findByBooking(booking);
        if (existingPayment.isPresent()) {
            throw new RuntimeException("Payment already exists for booking id: " + bookingId);
        }
        
        Payment payment = new Payment(booking, amount, paymentMethod);
        return paymentRepository.save(payment);
    }
    
    public Payment processPayment(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    payment.setPaymentStatus(Payment.PaymentStatus.Paid);
                    return paymentRepository.save(payment);
                })
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
    }
    
    public Payment failPayment(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    payment.setPaymentStatus(Payment.PaymentStatus.Failed);
                    return paymentRepository.save(payment);
                })
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
    }
    
    public List<Payment> getPaymentsByStatus(Payment.PaymentStatus status) {
        return paymentRepository.findByPaymentStatus(status);
    }
}