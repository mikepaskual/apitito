package com.apitito.api.v1.order.model;

import com.apitito.api.v1.dish.model.Dish;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

@Entity
@Table(name = "customer_order_items")
public class CustomerOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Dish dish;

    @ManyToOne(optional = false)
    private CustomerOrder customerOrder;

    @Min(1)
    private int quantity;

    // Public constructor
    public CustomerOrderItem() {
        super();
    }

    public BigDecimal getSubtotal() {
        return dish.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Dish dish;
        private CustomerOrder customerOrder;
        private int quantity;

        public Builder() {
            super();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder dish(Dish dish) {
            this.dish = dish;
            return this;
        }

        public Builder customerOrder(CustomerOrder customerOrder) {
            this.customerOrder = customerOrder;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public CustomerOrderItem build() {
            CustomerOrderItem customerOrderItem = new CustomerOrderItem();
            customerOrderItem.setCustomerOrder(this.customerOrder);
            customerOrderItem.setDish(this.dish);
            customerOrderItem.setId(this.id);
            customerOrderItem.setQuantity(this.quantity);
            return customerOrderItem;
        }
    }
}
