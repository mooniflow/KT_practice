package com.hello.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.dto.PetSitterDTO;
import com.hello.demo.dto.TimeRangeDTO;
import com.hello.demo.entity.PetSitter;
import com.hello.demo.entity.PetSize;
import com.hello.demo.entity.TimeRange;
import com.hello.demo.repository.PetSitterRepository;
import com.hello.demo.repository.UserRepository;

@Service
public class PetSitterService {

    @Autowired
    private PetSitterRepository petSitterRepository;

    @Autowired
    private UserRepository userRepository;

    // DTO를 Entity로 변환
    private PetSitter convertToEntity(PetSitterDTO dto) {
        PetSitter petSitter = new PetSitter();
        
        userRepository.findById(dto.getUserId())
            .ifPresent(petSitter::setUser);
        
        petSitter.setName(dto.getName());
        petSitter.setPhone(dto.getPhone());
        petSitter.setLocation(dto.getLocation());
        petSitter.setCertifications(dto.getCertifications());
        petSitter.setExperience(dto.getExperience());
        petSitter.setServices(dto.getServices());
        petSitter.setPetSize(PetSize.valueOf(dto.getPetSize()));
        petSitter.setPrice(dto.getPrice());
        petSitter.setActive(dto.isActive());
        petSitter.setIntroduction(dto.getIntroduction());
        
        List<TimeRange> availableTimes = dto.getAvailableTimes().stream()
            .map(timeRangeDTO -> {
                TimeRange timeRange = new TimeRange();
                timeRange.setStartTime(timeRangeDTO.getStartTime());
                timeRange.setEndTime(timeRangeDTO.getEndTime());
                timeRange.setPetSitter(petSitter);
                return timeRange;
            })
            .collect(Collectors.toList());
        petSitter.setAvailableTimes(availableTimes);
        
        return petSitter;
    }

    // Entity를 DTO로 변환
    private PetSitterDTO convertToDTO(PetSitter entity) {
        PetSitterDTO dto = new PetSitterDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setLocation(entity.getLocation());
        dto.setCertifications(entity.getCertifications());
        dto.setExperience(entity.getExperience());
        dto.setServices(entity.getServices());
        dto.setPetSize(entity.getPetSize().toString());
        dto.setPrice(entity.getPrice());
        dto.setActive(entity.isActive());
        dto.setIntroduction(entity.getIntroduction());
        
        List<TimeRangeDTO> timeRanges = entity.getAvailableTimes().stream()
            .map(timeRange -> {
                TimeRangeDTO timeRangeDTO = new TimeRangeDTO();
                timeRangeDTO.setStartTime(timeRange.getStartTime());
                timeRangeDTO.setEndTime(timeRange.getEndTime());
                return timeRangeDTO;
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

    public Optional<PetSitterDTO> getPetSitterByUserId(Long userId) {
        return petSitterRepository.findByUserId(userId)
                .map(this::convertToDTO);
    }

    public ResponseEntity<PetSitterDTO> updatePetSitter(Long id, PetSitterDTO dto) {
        return petSitterRepository.findById(id)
            .map(petSitter -> {
                updatePetSitterFields(petSitter, dto);
                return ResponseEntity.ok(convertToDTO(petSitterRepository.save(petSitter)));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private void updatePetSitterFields(PetSitter petSitter, PetSitterDTO dto) {
        petSitter.setName(dto.getName());
        petSitter.setPhone(dto.getPhone());
        petSitter.setLocation(dto.getLocation());
        petSitter.setCertifications(dto.getCertifications());
        petSitter.setExperience(dto.getExperience());
        petSitter.setServices(dto.getServices());
        petSitter.setPetSize(PetSize.valueOf(dto.getPetSize()));
        petSitter.setPrice(dto.getPrice());
        petSitter.setActive(dto.isActive());
        petSitter.setIntroduction(dto.getIntroduction());
        
        List<TimeRange> availableTimes = dto.getAvailableTimes().stream()
            .map(timeRangeDTO -> {
                TimeRange timeRange = new TimeRange();
                timeRange.setStartTime(timeRangeDTO.getStartTime());
                timeRange.setEndTime(timeRangeDTO.getEndTime());
                timeRange.setPetSitter(petSitter);
                return timeRange;
            })
            .collect(Collectors.toList());
        petSitter.setAvailableTimes(availableTimes);
    }
}
