package com.example.sport2.service;

import com.example.sport2.dto.ScheduleDTO;
import com.example.sport2.entity.Gym;
import com.example.sport2.entity.Schedule;
import com.example.sport2.repository.GymRepository;
import com.example.sport2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GymRepository gymRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, GymRepository gymRepository) {
        this.scheduleRepository = scheduleRepository;
        this.gymRepository = gymRepository;
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> new ScheduleDTO(
                        schedule.getId(),
                        schedule.getDateTime(),
                        schedule.getMaxParticipants(),
                        schedule.getDescription(),
                        schedule.getGym() != null ? schedule.getGym().getId() : null // Перевірка gym на null
                ))
                .collect(Collectors.toList());
    }

    public ScheduleDTO createSchedule(ScheduleDTO dto) {
        Gym gym = gymRepository.findById(dto.getGymId())
                .orElseThrow(() -> new IllegalArgumentException("Gym not found"));

        Schedule schedule = new Schedule();
        schedule.setDateTime(dto.getDateTime());
        schedule.setMaxParticipants(dto.getMaxParticipants());
        schedule.setDescription(dto.getDescription());
        schedule.setGym(gym);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return convertToDTO(savedSchedule);
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getDateTime(),
                schedule.getMaxParticipants(),
                schedule.getDescription(),
                schedule.getGym() != null ? schedule.getGym().getId() : null
        );
    }
}
