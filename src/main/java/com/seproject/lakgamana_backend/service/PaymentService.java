package com.seproject.lakgamana_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seproject.lakgamana_backend.entity.Payment;
import com.seproject.lakgamana_backend.repository.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    
    public Payment initiatePayment(Long reservationId, Double amount) {
        // Create and save payment
        Payment payment = new Payment();
        return paymentRepository.save(payment);
    }

    public void processRefund(Long paymentId) {
        // Placeholder: Implement actual refund logic
    }
}
