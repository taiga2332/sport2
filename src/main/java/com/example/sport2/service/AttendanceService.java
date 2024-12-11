package com.example.sport2.service;

import com.example.sport2.dto.AttendanceDTO;
import com.example.sport2.entity.Attendance;
import com.example.sport2.entity.Schedule;
import com.example.sport2.entity.User;
import com.example.sport2.repository.AttendanceRepository;
import com.example.sport2.repository.ScheduleRepository;
import com.example.sport2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public AttendanceDTO createAttendance(AttendanceDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Schedule schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        if (attendanceRepository.existsByUserAndSchedule(user, schedule)) {
            throw new IllegalArgumentException("User is already registered for this schedule");
        }

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setSchedule(schedule);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return new AttendanceDTO(
                savedAttendance.getUser().getId(),
                savedAttendance.getSchedule().getId()
        );
    }

    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendancesByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        return attendanceRepository.findByUserId(userId).stream()
                .map(attendance -> new AttendanceDTO(
                        attendance.getUser().getId(),
                        attendance.getSchedule().getId()
                ))
                .collect(Collectors.toList());
    }
}
