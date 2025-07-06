package com.apitito.api.v1.dish.service;

import com.apitito.api.v1.dish.dto.DishRequest;
import com.apitito.api.v1.dish.dto.DishResponse;
import com.apitito.api.v1.dish.mapper.DishMapper;
import com.apitito.api.v1.dish.model.Dish;
import com.apitito.api.v1.dish.repository.DishRepository;
import com.apitito.api.v1.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public DishResponse createDish(DishRequest request) {
        Dish dish = DishMapper.toEntity(request);
        Dish saved = dishRepository.save(dish);
        return DishMapper.toResponse(saved);
    }

    @Override
    public DishResponse getDishById(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + id));
        return DishMapper.toResponse(dish);
    }

    @Override
    public List<DishResponse> getAllDishes() {
        return dishRepository.findAll().stream()
                .map(DishMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DishResponse updateDish(Long id, DishRequest request) {
        Dish existing = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + id));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setCategory(request.getCategory());

        Dish updated = dishRepository.save(existing);
        return DishMapper.toResponse(updated);
    }

    @Override
    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dish not found with id: " + id);
        }
        dishRepository.deleteById(id);
    }
}
