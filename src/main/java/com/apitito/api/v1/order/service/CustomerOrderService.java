package com.apitito.api.v1.order.service;

import com.apitito.api.v1.order.dto.CustomerOrderRequest;
import com.apitito.api.v1.order.dto.CustomerOrderResponse;

import java.util.List;

public interface CustomerOrderService {

    CustomerOrderResponse create(CustomerOrderRequest request);

    CustomerOrderResponse getById(Long id);

    List<CustomerOrderResponse> getAll();

    void delete(Long id);
}
