package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import com.example.sport2.entity.Order;
import com.example.sport2.enums.Role;
import com.example.sport2.service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class OrdersController {

    private Role userRole;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Long> idColumn;

    @FXML
    private TableColumn<Order, LocalDateTime> dateTimeColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;

    @FXML
    private TextField dateTimeField;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button updateOrderButton;

    @FXML
    private Button deleteOrderButton;

    private final OrderService orderService;
    private ObservableList<Order> orderList;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setUserRole(Role role) {
        this.userRole = role;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadOrders();
    }

    private void loadOrders() {
        String currentUser = getCurrentUsername();
        Role role = MainApp.getInstance().getCurrentUserRole(); // Отримуємо роль

        List<Order> orders;
        if (role == Role.ADMIN) {
            orders = orderService.getAllOrders(); // Всі замовлення
        } else {
            if (currentUser == null || currentUser.isEmpty()) {
                showAlert("Не вдалося отримати ім'я користувача!");
                return;
            }
            orders = orderService.getOrdersByUsername(currentUser); // Лише свої замовлення
        }

        orderList = FXCollections.observableArrayList(orders);
        ordersTable.setItems(orderList);
    }


    @FXML
    public void addOrder() {
        try {
            if (dateTimeField.getText().isEmpty()) {
                showAlert("Поле дати та часу не може бути порожнім!");
                return;
            }

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            String currentUser = getCurrentUsername();
            if (currentUser == null || currentUser.isEmpty()) {
                showAlert("Не вдалося отримати ім'я користувача!");
                return;
            }

            orderService.createOrder(currentUser, currentUser, dateTime);

            loadOrders();
            dateTimeField.clear();
        } catch (DateTimeParseException e) {
            showAlert("Невірний формат дати та часу! Використовуйте формат: yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            showAlert("Помилка: " + e.getMessage());
        }
    }

    @FXML
    public void updateOrder() {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("Оберіть замовлення для редагування!");
            return;
        }

        try {
            LocalDateTime newDateTime = LocalDateTime.parse(dateTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            orderService.updateOrder(selectedOrder.getId(), newDateTime);
            loadOrders();
        } catch (Exception e) {
            showAlert("Помилка: " + e.getMessage());
        }
    }

    @FXML
    public void deleteOrder() {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("Оберіть замовлення для видалення!");
            return;
        }

        orderService.deleteOrder(selectedOrder.getId());
        loadOrders();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String getCurrentUsername() {
        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return null; // Користувач не авторизований
            }
            return authentication.getName();
        } catch (Exception e) {
            System.err.println("Помилка отримання імені користувача: " + e.getMessage());
            return null;
        }
    }

}
