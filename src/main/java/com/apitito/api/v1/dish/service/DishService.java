package com.apitito.api.v1.dish.service;

import com.apitito.api.v1.dish.dto.DishRequest;
import com.apitito.api.v1.dish.dto.DishResponse;

import java.util.List;

public interface DishService {

    DishResponse createDish(DishRequest request);

    DishResponse getDishById(Long id);

    List<DishResponse> getAllDishes();

    DishResponse updateDish(Long id, DishRequest request);

    void deleteDish(Long id);
}
