package com.apitito.api.v1.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CustomerOrderItemRequest {

    @NotNull
    private Long dishId;
    @Min(1)
    private int quantity;

    public CustomerOrderItemRequest() {
        super();
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
