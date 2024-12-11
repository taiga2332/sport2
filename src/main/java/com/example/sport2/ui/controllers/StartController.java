package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class StartController {

    @FXML
    public void goToLogin() {
        MainApp.getInstance().loadScene("/fxml/login.fxml", "Увійти", 800, 600);
    }

    @FXML
    public void goToRegistration() {
        MainApp.getInstance().loadScene("/fxml/registration.fxml", "Реєстрація", 800, 600);
    }
}
