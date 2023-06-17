package com.mpanchuk.orderservice.repository;

import com.mpanchuk.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
