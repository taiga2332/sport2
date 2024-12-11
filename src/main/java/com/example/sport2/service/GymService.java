package com.example.sport2.service;

import com.example.sport2.dto.GymDTO;
import com.example.sport2.entity.Gym;
import com.example.sport2.repository.GymRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymService {

    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<GymDTO> getAllGyms() {
        return gymRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GymDTO createGym(GymDTO dto) {
        validateGymDTO(dto);

        Gym gym = convertToEntity(dto);
        Gym savedGym = gymRepository.save(gym);

        return convertToDTO(savedGym);
    }

    // Валідація полів
    private void validateGymDTO(GymDTO dto) {
        if (!StringUtils.hasText(dto.getName())) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (!StringUtils.hasText(dto.getAddress())) {
            throw new IllegalArgumentException("Address is required.");
        }
        if (!StringUtils.hasText(dto.getDescription())) {
            throw new IllegalArgumentException("Description is required.");
        }
    }

    // Мапінг DTO -> Entity
    private Gym convertToEntity(GymDTO dto) {
        Gym gym = new Gym();
        gym.setName(dto.getName());
        gym.setAddress(dto.getAddress());
        gym.setDescription(dto.getDescription());
        return gym;
    }

    // Мапінг Entity -> DTO
    private GymDTO convertToDTO(Gym gym) {
        return new GymDTO(gym.getId(), gym.getName(), gym.getAddress(), gym.getDescription());
    }
}
