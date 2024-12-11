package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import com.example.sport2.enums.Role;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.sport2.entity.Order;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class OrdersController {

    private Role userRole;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Long> idColumn;

    @FXML
    private TableColumn<Order, String> orderNameColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;

    public void setUserRole(Role role) {
        this.userRole = role;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderNameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    public void goBack() {
        MainApp.getInstance().loadSceneWithRole("/fxml/main.fxml", "Головне меню", 800, 600, userRole);
    }
}
