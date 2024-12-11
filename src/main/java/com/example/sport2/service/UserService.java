package com.example.sport2.service;

import com.example.sport2.dto.CreateUserRequest;
import com.example.sport2.dto.UserDTO;
import com.example.sport2.dto.UserResponse;
import com.example.sport2.entity.User;
import com.example.sport2.enums.Role;
import com.example.sport2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Реєстрація нового користувача через DTO
    public void registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Користувач з таким ім'ям вже існує");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setActive(true);

        userRepository.save(user);
    }

    // Реєстрація через запит
    public UserResponse register(CreateUserRequest request) {
        return createUser(request);
    }

    // Отримання ролі користувача за іменем
    public String getUserRole(String username) {
        return userRepository.findByUsername(username)
                .map(user -> user.getRole().toString())
                .orElse("USER"); // Роль за замовчуванням
    }

    // Аутентифікація користувача
    public User authenticateAndReturnUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }

    // Додавання нового користувача
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // Отримання користувача за ID
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail()));
    }

    // Отримання всіх користувачів
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }

    // Оновлення даних користувача
    public UserResponse updateUser(Long id, CreateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        user.setRole(request.getRole() != null ? request.getRole() : user.getRole());
        user.setActive(true);

        User updatedUser = userRepository.save(user);
        return new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифрування паролю
        userRepository.save(user);
    }



    // Перевірка автентифікації
    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
