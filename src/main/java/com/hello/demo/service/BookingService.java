package com.hello.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.demo.dto.BookingDTO;
import com.hello.demo.dto.PaymentUpdateRequest;
import com.hello.demo.entity.Booking;
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
        dto.setSitterId(booking.getPetSitter().getId());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setService(booking.getService());
        dto.setStatus(booking.getStatus());
        dto.setLocation(booking.getLocation());
        dto.setPrice(booking.getPrice());
        dto.setPaymentStatus(booking.getPaymentStatus());
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
        
        userRepository.findById(dto.getUserId())
            .ifPresent(booking::setUser);
            
        petSitterRepository.findById(dto.getSitterId())
            .ifPresent(booking::setPetSitter);
        
        booking.setStartTime(dto.getStartTime());
        booking.setEndTime(dto.getEndTime());
        booking.setService(dto.getService());
        booking.setStatus("PENDING");
        booking.setLocation(dto.getLocation());
        booking.setPrice(dto.getPrice());
        booking.setPaymentStatus("UNPAID");
        
        return convertToDTO(bookingRepository.save(booking));
    }

    public List<BookingDTO> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public ResponseEntity<BookingDTO> updatePaymentStatus(Long id, PaymentUpdateRequest request) {
        return bookingRepository.findById(id)
            .map(booking -> {
                booking.setPaymentStatus(request.getPaymentStatus().toUpperCase());
                BookingDTO updatedBooking = convertToDTO(bookingRepository.save(booking));
                return ResponseEntity.ok(updatedBooking);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
