package com.hello.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hello.demo.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
