package com.apitito.api.v1.dish.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class DishRequest {

    @NotBlank
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @NotBlank
    private String category;

    public DishRequest() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
