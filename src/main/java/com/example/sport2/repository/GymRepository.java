package com.example.sport2.repository;

import com.example.sport2.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    // Додаткові методи можна додати за потреби
}
