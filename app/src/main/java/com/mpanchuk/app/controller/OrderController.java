package com.mpanchuk.app.controller;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/order")
@Valid
public class OrderController implements SecuredRestController {

//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping(value = "make")
//    public ResponseEntity<OrderResponse> makeOrder(@RequestBody CreateOrderDto createOrderDto) {
//        orderService.makeOrder(createOrderDto);
//        return ResponseEntity.ok().build();
//    }

}
