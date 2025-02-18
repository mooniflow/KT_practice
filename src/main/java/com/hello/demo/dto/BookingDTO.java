package com.hello.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long sitterId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String service;
    private String status;
    private String location;
    private int price;
    private String paymentStatus;
}
