package com.apitito.api.v1.order.controller;

import com.apitito.api.v1.order.dto.CustomerOrderRequest;
import com.apitito.api.v1.order.dto.CustomerOrderResponse;
import com.apitito.api.v1.order.service.CustomerOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService orderService;

    @PostMapping
    public ResponseEntity<CustomerOrderResponse> create(@Valid @RequestBody CustomerOrderRequest request) {
        CustomerOrderResponse response = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrderResponse>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
