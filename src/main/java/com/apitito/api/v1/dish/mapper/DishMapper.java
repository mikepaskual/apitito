package com.apitito.api.v1.dish.mapper;

import com.apitito.api.v1.dish.dto.DishRequest;
import com.apitito.api.v1.dish.dto.DishResponse;
import com.apitito.api.v1.dish.model.Dish;

public class DishMapper {

    private DishMapper() {
        super();
    }

    public static Dish toEntity(DishRequest request) {
        return Dish.builder()
                .category(request.getCategory())
                .description(request.getDescription())
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }

    public static DishResponse toResponse(Dish dish) {
        DishResponse response = new DishResponse();
        response.setCategory(dish.getCategory());
        response.setCreationDate(dish.getCreationDate());
        response.setDescription(dish.getDescription());
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setPrice(dish.getPrice());
        return response;
    }
}
