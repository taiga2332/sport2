package com.example.sport2.controller;

import com.example.sport2.dto.AttendanceDTO;
import com.example.sport2.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@Validated
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO createdAttendance = attendanceService.createAttendance(attendanceDTO);
        return ResponseEntity.ok(createdAttendance);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByUser(@PathVariable Long userId) {
        List<AttendanceDTO> attendances = attendanceService.getAttendancesByUser(userId);
        return ResponseEntity.ok(attendances);
    }
}
