package com.example.sport2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // Ім'я користувача, який створив замовлення

    private LocalDateTime dateTime; // Дата і час бронювання

    private String status; // Статус замовлення (наприклад, "Підтверджено", "Скасовано")

    @Column(name = "client_name", nullable = false)
    private String clientName;

    // Геттери та сеттери
    public String getClientName() {
        return clientName;
    }

    // Геттери та сеттери
    public Long getId() {
        return id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
