package com.hello.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sitter_id", nullable = false)
    private PetSitter petSitter;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String service;
    private String status; // pending, confirmed, rejected
    private String location;
    private int price;
    private String paymentStatus = "UNPAID"; // UNPAID, PAID, FAILED
}
