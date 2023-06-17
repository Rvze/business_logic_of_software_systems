package com.mpanchuk.app.controller;

import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import com.mpanchuk.app.domain.response.OrderResponse;
import com.mpanchuk.app.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/order")
@Valid
public class OrderController implements SecuredRestController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "make")
    public ResponseEntity<OrderResponse> makeOrder(@RequestBody CreateOrderDto createOrderDto) {
        orderService.makeOrder(createOrderDto);
        return ResponseEntity.ok().build();
    }

}
