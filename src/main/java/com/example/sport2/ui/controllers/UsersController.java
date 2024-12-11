package com.example.sport2.ui.controllers;

import com.example.sport2.MainApp;
import com.example.sport2.entity.User;
import com.example.sport2.enums.Role;
import com.example.sport2.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class UsersController {

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Long> idColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, Role> roleColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private ChoiceBox<Role> roleChoiceBox;

    @FXML
    private Button addUserButton;

    @FXML
    private Button updateUserButton;

    @FXML
    private Button deleteUserButton;

    private Role userRole;

    private final UserService userService;
    private ObservableList<User> userList;

    public void setUserRole(Role role) {
        this.userRole = role;
    }

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void initialize() {
        roleChoiceBox.getItems().addAll(Role.USER, Role.ADMIN);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadUsers();

        usersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateFields(newValue));
    }

    private void loadUsers() {
        userList = FXCollections.observableArrayList(userService.findAllUsers());
        usersTable.setItems(userList);
    }

    private void populateFields(User user) {
        if (user != null) {
            usernameField.setText(user.getUsername());
            emailField.setText(user.getEmail());
            roleChoiceBox.setValue(user.getRole());
        }
    }

    @FXML
    public void addUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        Role role = roleChoiceBox.getValue();

        if (username.isEmpty() || email.isEmpty() || role == null) {
            showAlert("Усі поля повинні бути заповнені!");
            return;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setRole(role);
        newUser.setPassword("defaultPassword"); // Додати пароль за замовчуванням
        userService.saveUser(newUser);
        loadUsers();
        clearFields();
    }

    @FXML
    public void updateUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Оберіть користувача для редагування!");
            return;
        }

        selectedUser.setUsername(usernameField.getText());
        selectedUser.setEmail(emailField.getText());
        selectedUser.setRole(roleChoiceBox.getValue());
        userService.saveUser(selectedUser);
        loadUsers();
        clearFields();
    }

    @FXML
    public void deleteUser() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Оберіть користувача для видалення!");
            return;
        }

        userService.deleteUser(selectedUser.getId());
        loadUsers();
        clearFields();
    }

    @FXML
    public void goBack() {
        MainApp.getInstance().loadSceneWithRole("/fxml/main.fxml", "Головне меню", 800, 600, userRole);
    }




    private void clearFields() {
        usernameField.clear();
        emailField.clear();
        roleChoiceBox.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
