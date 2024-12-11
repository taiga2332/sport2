package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.sport2.entity.Order;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class OrdersController {

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Long> idColumn;

    @FXML
    private TableColumn<Order, String> orderNameColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderNameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ordersTable.getItems().addAll(
                new Order(1L, "Замовлення 1", "Виконано"),
                new Order(2L, "Замовлення 2", "Очікує"),
                new Order(3L, "Замовлення 3", "Скасовано")
        );
    }

    @FXML
    public void goBack() {
        MainApp.getInstance().loadScene("/fxml/main.fxml", "Головне меню", 800, 600);
    }
}
