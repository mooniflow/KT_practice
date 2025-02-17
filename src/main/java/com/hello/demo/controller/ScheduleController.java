package com.hello.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hello.demo.dto.ScheduleDTO;
import com.hello.demo.service.ScheduleService;
import java.util.List;

@RestController
@RequestMapping("/api/sitter-schedules")
@CrossOrigin(origins = "http://localhost:8082")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDTO> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.saveSchedule(scheduleDTO));
    }

    @GetMapping("/{sitterId}")
    public ResponseEntity<List<ScheduleDTO>> getSitterSchedules(@PathVariable Long sitterId) {
        return ResponseEntity.ok(scheduleService.getSchedulesBySitterId(sitterId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
