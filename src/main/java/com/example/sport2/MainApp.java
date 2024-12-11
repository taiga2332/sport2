package com.example.sport2;

import com.example.sport2.config.SpringFXMLLoader;
import com.example.sport2.enums.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;

import java.io.IOException;

public class MainApp extends Application {

    private static ConfigurableApplicationContext context;
    private static MainApp instance; // Додаємо інстанс для доступу
    private Stage primaryStage;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(Sport2Application.class).run();
        instance = this; // Ініціалізуємо інстанс
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        loadScene("/fxml/start.fxml", "Ласкаво просимо", 800, 600);
    }

    public void loadScene(String fxmlPath, String title, int width, int height) {
        try {
            SpringFXMLLoader loader = context.getBean(SpringFXMLLoader.class);
            FXMLLoader fxmlLoader = loader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSceneWithRole(String fxmlPath, String title, int width, int height, Role role) {
        try {
            SpringFXMLLoader loader = context.getBean(SpringFXMLLoader.class);
            FXMLLoader fxmlLoader = loader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            // Передаємо роль у контролер
            Object controller = fxmlLoader.getController();
            if (controller instanceof com.example.sport2.ui.controllers.MainController mainController) {
                mainController.setUserRole(role);
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MainApp getInstance() {
        return instance; // Повертаємо інстанс
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
