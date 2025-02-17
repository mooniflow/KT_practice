package com.hello.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {
    private Long id;
    private Long sitterId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
