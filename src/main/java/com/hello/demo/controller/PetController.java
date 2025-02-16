package com.hello.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hello.demo.entity.Pet;
import com.hello.demo.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    // 반려동물 등록
    @PostMapping("/register")
    public ResponseEntity<Pet> registerPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.savePet(pet));
    }

    // 반려동물 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") Long id, @RequestBody Pet updatedPet) {
        return petService.updatePet(id, updatedPet);
    }

    // 모든 반려동물 조회
    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    // 특정 반려동물 조회
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 반려동물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
