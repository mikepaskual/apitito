package com.apitito.api.v1.dish.controller;

import com.apitito.api.v1.dish.dto.DishRequest;
import com.apitito.api.v1.dish.dto.DishResponse;
import com.apitito.api.v1.dish.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@Valid @RequestBody DishRequest request) {
        DishResponse response = dishService.createDish(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id) {
        DishResponse response = dishService.getDishById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        List<DishResponse> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Long id, @Valid @RequestBody DishRequest request) {
        DishResponse response = dishService.updateDish(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

}
