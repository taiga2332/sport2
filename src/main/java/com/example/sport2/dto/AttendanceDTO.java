package com.example.sport2.dto;

public class AttendanceDTO {

    private Long userId;
    private Long scheduleId;

    public AttendanceDTO() {}

    public AttendanceDTO(Long userId, Long scheduleId) {
        this.userId = userId;
        this.scheduleId = scheduleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
