package com.hello.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.dto.BookingDTO;
import com.hello.demo.entity.Booking;
import com.hello.demo.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setUserName(booking.getUser().getUsername());
        dto.setPetId(booking.getPet().getId());
        dto.setPetName(booking.getPet().getName());
        dto.setSitterId(booking.getPetSitter().getId());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setService(booking.getService());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    public List<BookingDTO> getBookingsBySitterId(Long sitterId) {
        return bookingRepository.findByPetSitterId(sitterId).stream()
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
}
