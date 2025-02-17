package com.hello.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hello.demo.dto.ScheduleDTO;
import com.hello.demo.entity.Schedule;
import com.hello.demo.repository.ScheduleRepository;
import com.hello.demo.repository.PetSitterRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private PetSitterRepository petSitterRepository;

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setSitterId(schedule.getPetSitter().getId());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        return dto;
    }

    private Schedule convertToEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        petSitterRepository.findById(dto.getSitterId())
            .ifPresent(schedule::setPetSitter);
        return schedule;
    }

    public ScheduleDTO saveSchedule(ScheduleDTO dto) {
        Schedule schedule = convertToEntity(dto);
        return convertToDTO(scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> getSchedulesBySitterId(Long sitterId) {
        return scheduleRepository.findByPetSitterId(sitterId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
} 