package com.hello.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class PetSitter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String name;
    private String location;
    
    @ElementCollection
    @CollectionTable(name = "petsitter_certifications")
    private List<String> certifications;
    
    @ElementCollection
    @CollectionTable(name = "petsitter_available_times")
    private List<List<LocalDateTime>> availableTimes;
    
    private String experience;
    private String services;
    
    @Enumerated(EnumType.STRING)
    private PetSize petSize;
    
    private int price;
    private boolean isActive;
}
