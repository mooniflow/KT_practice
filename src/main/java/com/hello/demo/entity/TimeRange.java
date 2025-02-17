package com.hello.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "petsitter_available_times")
@Getter
@Setter
public class TimeRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "pet_sitter_id")
    private PetSitter petSitter;

    public void setPetSitter(PetSitter petSitter) {
        this.petSitter = petSitter;
    }

    public PetSitter getPetSitter() {
        return petSitter;
    }
} 