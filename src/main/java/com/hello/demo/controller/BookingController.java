package com.hello.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.demo.dto.BookingDTO;
import com.hello.demo.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:8082")
@Tag(name = "Booking", description = "예약 관리 API")
@Validated
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Operation(summary = "대기중인 예약 조회", description = "특정 펫시터의 대기중인 예약을 조회합니다")
    @Parameter(name = "sitterId", description = "펫시터 ID", required = true)
    @GetMapping("/pending/{sitterId}")
    public ResponseEntity<List<BookingDTO>> getPendingBookings(
            @PathVariable("sitterId") @Min(1) Long sitterId) {
        return ResponseEntity.ok(bookingService.getBookingsByStatusAndSitter(sitterId, "PENDING"));
    }

    @Operation(summary = "승인된 예약 조회", description = "특정 펫시터의 승인된 예약을 조회합니다")
    @Parameter(name = "sitterId", description = "펫시터 ID", required = true)
    @GetMapping("/approved/{sitterId}")
    public ResponseEntity<List<BookingDTO>> getApprovedBookings(
            @PathVariable("sitterId") @Min(1) Long sitterId) {
        return ResponseEntity.ok(bookingService.getBookingsByStatusAndSitter(sitterId, "APPROVED"));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<BookingDTO> approveBooking(@PathVariable("id") Long id) {
        return bookingService.updateBookingStatus(id, "APPROVED");
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<BookingDTO> rejectBooking(@PathVariable("id") Long id) {
        return bookingService.updateBookingStatus(id, "REJECTED");
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<BookingDTO> completeBooking(@PathVariable("id") Long id) {
        return bookingService.updateBookingStatus(id, "COMPLETED");
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable("id") Long id) {
        return bookingService.updateBookingStatus(id, "CANCELLED");
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
            return ResponseEntity.ok(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
