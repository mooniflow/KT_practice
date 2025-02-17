package com.hello.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.demo.dto.PetSitterDTO;
import com.hello.demo.service.PetSitterService;

@RestController
@RequestMapping("/api/pet-sitters")
@CrossOrigin(origins = "http://localhost:8082")
public class PetSitterController {

    @Autowired
    private PetSitterService petSitterService;

    // 펫시터 등록
    @PostMapping
    public ResponseEntity<PetSitterDTO> registerPetSitter(@RequestBody PetSitterDTO petSitterDTO) {
        return ResponseEntity.ok(petSitterService.savePetSitter(petSitterDTO));
    }

    // 모든 펫시터 조회
    @GetMapping
    public ResponseEntity<List<PetSitterDTO>> getAllPetSitters() {
        return ResponseEntity.ok(petSitterService.getAllPetSitters());
    }

    // 활성화된 펫시터만 조회
    @GetMapping("/active")
    public ResponseEntity<List<PetSitterDTO>> getActivePetSitters() {
        return ResponseEntity.ok(petSitterService.getActivePetSitters());
    }

    // 펫시터 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<PetSitterDTO> updatePetSitter(
            @PathVariable Long id,
            @RequestBody PetSitterDTO petSitterDTO) {
        return petSitterService.updatePetSitter(id, petSitterDTO);
    }
}
