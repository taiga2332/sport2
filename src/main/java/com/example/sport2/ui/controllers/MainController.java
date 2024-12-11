package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import com.example.sport2.enums.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    @FXML
    private Button adminButton;

    private Role userRole;

    public void setUserRole(Role role) {
        this.userRole = role;
        updateUIBasedOnRole();
    }

    private void updateUIBasedOnRole() {
        if (userRole == Role.ADMIN) {
            adminButton.setVisible(true);
        } else {
            adminButton.setVisible(false);
        }
    }

    @FXML
    public void viewOrders() {
        OrdersController ordersController = MainApp.getInstance()
                .loadSceneWithRole("/fxml/orders.fxml", "Список замовлень", 800, 600, userRole);

        if (ordersController != null) {
            ordersController.setUserRole(userRole);
        }
    }



    @FXML
    public void manageUsers() {
        MainApp.getInstance().loadScene("/fxml/manageUsers.fxml", "Користувачі", 800, 600);
    }

    @FXML
    public void logout() {
        MainApp.getInstance().loadScene("/fxml/login.fxml", "Увійти", 800, 600);
    }
}
