package com.apitito.api.v1.order.mapper;

import com.apitito.api.v1.dish.model.Dish;
import com.apitito.api.v1.order.dto.CustomerOrderItemRequest;
import com.apitito.api.v1.order.dto.CustomerOrderItemResponse;
import com.apitito.api.v1.order.dto.CustomerOrderRequest;
import com.apitito.api.v1.order.dto.CustomerOrderResponse;
import com.apitito.api.v1.order.model.CustomerOrder;
import com.apitito.api.v1.order.model.CustomerOrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderMapper {

    private CustomerOrderMapper() {
        super();
    }

    public static CustomerOrder toEntity(CustomerOrderRequest request, List<CustomerOrderItem> items) {
        return CustomerOrder.builder()
                .tableNumber(request.getTableNumber())
                .status(request.getStatus())
                .items(items)
                .build();
    }

    public static CustomerOrderItem toItemEntity(CustomerOrderItemRequest request, Dish dish, CustomerOrder order) {
        return CustomerOrderItem.builder()
                .dish(dish)
                .quantity(request.getQuantity())
                .customerOrder(order)
                .build();
    }

    public static CustomerOrderResponse toResponse(CustomerOrder order) {
        List<CustomerOrderItemResponse> itemResponses = order.getItems().stream()
                .map(CustomerOrderMapper::toItemResponse)
                .collect(Collectors.toList());

        return new CustomerOrderResponse() {{
            setId(order.getId());
            setTableNumber(order.getTableNumber());
            setStatus(order.getStatus());
            setCreatedAt(order.getCreationDate());
            setTotalPrice(order.getTotalPrice());
            setItems(itemResponses);
        }};
    }

    public static CustomerOrderItemResponse toItemResponse(CustomerOrderItem item) {
        return new CustomerOrderItemResponse() {{
            setDishId(item.getDish().getId());
            setDishName(item.getDish().getName());
            setUnitPrice(item.getDish().getPrice());
            setQuantity(item.getQuantity());
            setSubtotal(item.getSubtotal());
        }};
    }
}
