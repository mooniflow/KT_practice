package com.hello.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetSitterDTO {
    private Long id;
    private Long userId;
    private String name;
    private String location;
    private List<String> certifications;
    private List<TimeRangeDTO> availableTimes;
    private String experience;
    private String services;
    private int petSize;
    private int price;
    private boolean isActive;
}
