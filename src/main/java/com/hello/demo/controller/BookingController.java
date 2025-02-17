package com.hello.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hello.demo.dto.BookingDTO;
import com.hello.demo.service.BookingService;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:8082")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/sitter/{sitterId}")
    public ResponseEntity<List<BookingDTO>> getSitterBookings(@PathVariable Long sitterId) {
        return ResponseEntity.ok(bookingService.getBookingsBySitterId(sitterId));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<BookingDTO> confirmBooking(@PathVariable Long id) {
        return bookingService.updateBookingStatus(id, "confirmed");
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<BookingDTO> rejectBooking(@PathVariable Long id) {
        return bookingService.updateBookingStatus(id, "rejected");
    }
}
