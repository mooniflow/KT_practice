package com.hello.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.dto.BookingDTO;
import com.hello.demo.entity.Booking;
import com.hello.demo.entity.Pet;
import com.hello.demo.entity.PetSitter;
import com.hello.demo.entity.User;
import com.hello.demo.repository.BookingRepository;
import com.hello.demo.repository.PetRepository;
import com.hello.demo.repository.PetSitterRepository;
import com.hello.demo.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetSitterRepository petSitterRepository;

    @Autowired
    private PetRepository petRepository;

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setUserName(booking.getUser().getUsername());
        dto.setPetId(booking.getPet().getId());
        dto.setPetName(booking.getPet().getName());
        dto.setSitterId(booking.getPetSitter().getId());
        dto.setSitterName(booking.getPetSitter().getName());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setService(booking.getService());
        dto.setStatus(booking.getStatus());
        dto.setLocation(booking.getLocation());
        dto.setPrice(booking.getPrice());
        return dto;
    }

    public List<BookingDTO> getBookingsBySitterId(Long sitterId) {
        return bookingRepository.findByPetSitterId(sitterId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByStatusAndSitter(Long sitterId, String status) {
        return bookingRepository.findByPetSitterIdAndStatus(sitterId, status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<BookingDTO> updateBookingStatus(Long id, String status) {
        return bookingRepository.findById(id)
            .map(booking -> {
                booking.setStatus(status);
                return ResponseEntity.ok(convertToDTO(bookingRepository.save(booking)));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public BookingDTO createBooking(BookingDTO dto) {
        Booking booking = new Booking();
        
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        PetSitter sitter = petSitterRepository.findById(dto.getSitterId())
            .orElseThrow(() -> new RuntimeException("PetSitter not found"));
        Pet pet = petRepository.findById(dto.getPetId())
            .orElseThrow(() -> new RuntimeException("Pet not found"));
        
        booking.setUser(user);
        booking.setPetSitter(sitter);
        booking.setPet(pet);
        
        booking.setStartTime(dto.getStartTime());
        booking.setEndTime(dto.getEndTime());
        booking.setService(dto.getService());
        booking.setStatus("PENDING");
        booking.setLocation(sitter.getLocation());
        booking.setPrice(sitter.getPrice());
        
        return convertToDTO(bookingRepository.save(booking));
    }
}
