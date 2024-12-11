package com.example.sport2.entity;

public class Order {

    private Long id;
    private String orderName;
    private String status;

    public Order(Long id, String orderName, String status) {
        this.id = id;
        this.orderName = orderName;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
