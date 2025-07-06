package com.apitito.api.v1.dish.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank
    private String category;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    // Public constructor
    public Dish() {
        super();
    }

    // Private constructor for builder
    private Dish(Builder builder) {
        this.category = builder.category;
        this.creationDate = builder.creationDate;
        this.description = builder.description;
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
    }

    @PrePersist
    private void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    // Builder pattern
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private String category;
        private LocalDateTime creationDate;

        public Builder() {
            super();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Dish build() {
            return new Dish(this);
        }
    }
}
