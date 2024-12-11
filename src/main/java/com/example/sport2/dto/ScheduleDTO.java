package com.example.sport2.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ScheduleDTO {

    private Long id;

    @NotNull(message = "Date and time cannot be null")
    private LocalDateTime dateTime;

    @NotNull(message = "Maximum participants cannot be null")
    private Integer maxParticipants;

    private String description;

    @NotNull(message = "Gym ID cannot be null")
    private Long gymId;

    public ScheduleDTO() {}

    public ScheduleDTO(Long id, LocalDateTime dateTime, Integer maxParticipants, String description, Long gymId) {
        this.id = id;
        this.dateTime = dateTime;
        this.maxParticipants = maxParticipants;
        this.description = description;
        this.gymId = gymId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
