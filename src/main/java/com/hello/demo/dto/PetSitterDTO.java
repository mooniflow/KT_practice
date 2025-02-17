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
    private String phone;
    private String location;
    private List<String> certifications;
    private String experience;
    private String services;
    private String petSize;
    private int price;
    private boolean isActive;
    private String introduction;
    private List<TimeRangeDTO> availableTimes;
}
