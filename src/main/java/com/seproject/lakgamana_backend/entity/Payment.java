package com.seproject.lakgamana_backend.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GenericGenerator(name = "payment4", strategy = "com.seproject.lakgamana_backend.shared.id.FourDigitPaymentIdGenerator")
    @GeneratedValue(generator = "payment4")
    private Long id;

    public Payment() {}
    public Payment(Long id) { this.id = id; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}