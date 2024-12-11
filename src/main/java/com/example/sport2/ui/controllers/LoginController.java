package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import com.example.sport2.entity.User;
import com.example.sport2.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @Autowired
    private UserService userService;

    @FXML
    public void loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User authenticatedUser = userService.authenticateAndReturnUser(username, password);

        if (authenticatedUser != null) {
            // Використовуємо інстанс MainApp для виклику loadSceneWithRole
            MainApp.getInstance().loadSceneWithRole(
                    "/fxml/main.fxml",
                    "Головна сторінка",
                    800,
                    600,
                    authenticatedUser.getRole()
            );
        } else {
            System.out.println("Невірний логін або пароль!");
        }
    }
}