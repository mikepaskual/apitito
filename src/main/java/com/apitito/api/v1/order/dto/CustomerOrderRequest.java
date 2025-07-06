package com.apitito.api.v1.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderRequest {

    private Integer tableNumber;
    @NotNull
    private String status;
    private List<CustomerOrderItemRequest> items;

    public CustomerOrderRequest() {
        items = new ArrayList<>();
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<CustomerOrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<CustomerOrderItemRequest> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = new ArrayList<>(items);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
