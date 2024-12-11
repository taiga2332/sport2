package com.example.sport2.repository;

import com.example.sport2.entity.Attendance;
import com.example.sport2.entity.Schedule;
import com.example.sport2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUserId(Long userId);

    boolean existsByUserAndSchedule(User user, Schedule schedule);

}
