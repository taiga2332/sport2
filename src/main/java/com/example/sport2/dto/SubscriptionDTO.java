package com.example.sport2.dto;

import jakarta.validation.constraints.NotNull;

public class SubscriptionDTO {

    private Long id;

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Session count is required")
    private Integer sessionCount;

    @NotNull(message = "Validity in days is required")
    private Integer validityInDays;

    public SubscriptionDTO() {}

    public SubscriptionDTO(Long id, String type, Double price, Integer sessionCount, Integer validityInDays) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.sessionCount = sessionCount;
        this.validityInDays = validityInDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(Integer sessionCount) {
        this.sessionCount = sessionCount;
    }

    public Integer getValidityInDays() {
        return validityInDays;
    }

    public void setValidityInDays(Integer validityInDays) {
        this.validityInDays = validityInDays;
    }
}
