package com.example.sport2.controller;

import com.example.sport2.dto.GymDTO;
import com.example.sport2.service.GymService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping
    public ResponseEntity<List<GymDTO>> getAllGyms() {
        List<GymDTO> gyms = gymService.getAllGyms();
        return ResponseEntity.ok(gyms);
    }

    @PostMapping
    public ResponseEntity<GymDTO> createGym(@RequestBody GymDTO gymDTO) {
        GymDTO createdGym = gymService.createGym(gymDTO);
        return ResponseEntity.ok(createdGym);
    }
}
