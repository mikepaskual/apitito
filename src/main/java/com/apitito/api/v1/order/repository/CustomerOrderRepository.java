package com.apitito.api.v1.order.repository;

import com.apitito.api.v1.order.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
