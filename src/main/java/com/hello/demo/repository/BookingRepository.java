package com.hello.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.demo.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPetSitterId(Long sitterId);
    List<Booking> findByPetSitterIdAndStatus(Long sitterId, String status);
}
