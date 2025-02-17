package com.hello.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeRangeDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
