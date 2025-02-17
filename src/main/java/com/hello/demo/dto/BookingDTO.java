package com.hello.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long petId;
    private String petName;
    private Long sitterId;
    private String sitterName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String service;
    private String status;
    private String location;
    private int price;
}
