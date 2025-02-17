package com.hello.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.demo.entity.PetSitter;
import com.hello.demo.entity.PetSize;

public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {
    List<PetSitter> findByIsActiveTrue();
    List<PetSitter> findByPetSize(PetSize petSize);
}
