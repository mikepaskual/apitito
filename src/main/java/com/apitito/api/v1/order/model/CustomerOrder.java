package com.apitito.api.v1.order.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tableNumber;

    @NotBlank
    private String status;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerOrderItem> items;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    // Public constructor
    public CustomerOrder() {
        items = new ArrayList<>();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CustomerOrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<CustomerOrderItem> getItems() {
        return items;
    }

    public void setItems(List<CustomerOrderItem> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = new ArrayList<>(items);
        }
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @PrePersist
    private void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Integer tableNumber;
        private String status;
        private LocalDateTime creationDate;
        private List<CustomerOrderItem> items;

        public Builder() {
            super();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder tableNumber(Integer tableNumber) {
            this.tableNumber = tableNumber;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder items(List<CustomerOrderItem> items) {
            this.items = items;
            return this;
        }

        public CustomerOrder build() {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setCreationDate(this.creationDate);
            customerOrder.setId(this.id);
            customerOrder.setItems(this.items);
            customerOrder.setStatus(this.status);
            customerOrder.setTableNumber(this.tableNumber);
            return customerOrder;
        }
    }
}
