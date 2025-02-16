package com.hello.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.entity.Pet;
import com.hello.demo.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public ResponseEntity<Pet> updatePet(Long id, Pet updatedPet) {
        return petRepository.findById(id)
            .map(pet -> {
                pet.setName(updatedPet.getName());
                pet.setType(updatedPet.getType());
                pet.setAge(updatedPet.getAge());
                pet.setPhoto(updatedPet.getPhoto());
                return ResponseEntity.ok(petRepository.save(pet));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
