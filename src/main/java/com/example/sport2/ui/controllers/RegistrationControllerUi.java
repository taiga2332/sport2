package com.example.sport2.ui.controllers;

import com.example.sport2.dto.UserDTO;
import com.example.sport2.enums.Role;
import com.example.sport2.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

@Component // Додаємо анотацію
public class RegistrationControllerUi {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private ChoiceBox<Role> roleChoiceBox;

    @FXML
    private Button registerButton;

    @FXML
    private Label successLabel;

    private final UserService userService;

    public RegistrationControllerUi(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void initialize() {
        roleChoiceBox.getItems().addAll(Role.USER, Role.ADMIN);
        roleChoiceBox.setValue(Role.USER);
    }

    @FXML
    public void registerUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        Role role = roleChoiceBox.getValue();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            successLabel.setText("Всі поля обов'язкові!");
            return;
        }

        UserDTO userDTO = new UserDTO(username, email, password, role);

        try {
            userService.registerUser(userDTO);
            successLabel.setText("Користувач успішно зареєстрований!");
        } catch (Exception e) {
            successLabel.setText("Помилка реєстрації: " + e.getMessage());
        }
    }
}
