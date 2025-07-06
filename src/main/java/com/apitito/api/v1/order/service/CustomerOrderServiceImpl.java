package com.apitito.api.v1.order.service;

import com.apitito.api.v1.dish.model.Dish;
import com.apitito.api.v1.dish.repository.DishRepository;
import com.apitito.api.v1.exception.ResourceNotFoundException;
import com.apitito.api.v1.order.dto.CustomerOrderRequest;
import com.apitito.api.v1.order.dto.CustomerOrderResponse;
import com.apitito.api.v1.order.mapper.CustomerOrderMapper;
import com.apitito.api.v1.order.model.CustomerOrder;
import com.apitito.api.v1.order.model.CustomerOrderItem;
import com.apitito.api.v1.order.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    public CustomerOrderResponse create(CustomerOrderRequest request) {
        CustomerOrder order = new CustomerOrder.Builder()
                .tableNumber(request.getTableNumber())
                .status(request.getStatus())
                .build();

        List<CustomerOrderItem> items = request.getItems().stream()
                .map(itemReq -> {
                    Dish dish = dishRepository.findById(itemReq.getDishId())
                            .orElseThrow(() -> new ResourceNotFoundException("Dish not found: " + itemReq.getDishId()));
                    return new CustomerOrderItem.Builder()
                            .dish(dish)
                            .quantity(itemReq.getQuantity())
                            .customerOrder(order)
                            .build();
                })
                .collect(Collectors.toList());

        order.getItems().addAll(items);

        return CustomerOrderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public CustomerOrderResponse getById(Long id) {
        CustomerOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        return CustomerOrderMapper.toResponse(order);
    }

    @Override
    public List<CustomerOrderResponse> getAll() {
        return orderRepository.findAll().stream()
                .map(CustomerOrderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }
}
