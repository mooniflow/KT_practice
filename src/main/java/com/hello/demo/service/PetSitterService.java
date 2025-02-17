package com.hello.demo.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.dto.PetSitterDTO;
import com.hello.demo.dto.TimeRangeDTO;
import com.hello.demo.entity.PetSitter;
import com.hello.demo.entity.PetSize;
import com.hello.demo.repository.PetSitterRepository;

@Service
public class PetSitterService {

    @Autowired
    private PetSitterRepository petSitterRepository;

    // DTO를 Entity로 변환
    private PetSitter convertToEntity(PetSitterDTO dto) {
        PetSitter petSitter = new PetSitter();
        petSitter.setName(dto.getName());
        petSitter.setLocation(dto.getLocation());
        petSitter.setCertifications(dto.getCertifications());
        petSitter.setExperience(dto.getExperience());
        petSitter.setServices(dto.getServices());
        petSitter.setPetSize(PetSize.fromValue(dto.getPetSize()));
        petSitter.setPrice(dto.getPrice());
        petSitter.setActive(dto.isActive());
        
        // TimeRangeDTO 리스트를 LocalDateTime 이중 리스트로 변환
        List<List<LocalDateTime>> availableTimes = dto.getAvailableTimes().stream()
            .map(timeRange -> Arrays.asList(timeRange.getStartTime(), timeRange.getEndTime()))
            .collect(Collectors.toList());
        petSitter.setAvailableTimes(availableTimes);
        
        return petSitter;
    }

    // Entity를 DTO로 변환
    private PetSitterDTO convertToDTO(PetSitter entity) {
        PetSitterDTO dto = new PetSitterDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setCertifications(entity.getCertifications());
        dto.setExperience(entity.getExperience());
        dto.setServices(entity.getServices());
        dto.setPetSize(entity.getPetSize().getValue());
        dto.setPrice(entity.getPrice());
        dto.setActive(entity.isActive());
        
        // LocalDateTime 이중 리스트를 TimeRangeDTO 리스트로 변환
        List<TimeRangeDTO> timeRanges = entity.getAvailableTimes().stream()
            .map(times -> {
                TimeRangeDTO timeRange = new TimeRangeDTO();
                timeRange.setStartTime(times.get(0));
                timeRange.setEndTime(times.get(1));
                return timeRange;
            })
            .collect(Collectors.toList());
        dto.setAvailableTimes(timeRanges);
        
        return dto;
    }

    public PetSitterDTO savePetSitter(PetSitterDTO dto) {
        PetSitter petSitter = convertToEntity(dto);
        return convertToDTO(petSitterRepository.save(petSitter));
    }

    public List<PetSitterDTO> getAllPetSitters() {
        return petSitterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PetSitterDTO> getActivePetSitters() {
        return petSitterRepository.findByIsActiveTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<PetSitterDTO> updatePetSitter(Long id, PetSitterDTO dto) {
        return petSitterRepository.findById(id)
            .map(petSitter -> {
                PetSitter updated = convertToEntity(dto);
                updated.setId(id);
                return ResponseEntity.ok(convertToDTO(petSitterRepository.save(updated)));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
