package com.hello.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.demo.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPetSitterId(Long sitterId);
} 