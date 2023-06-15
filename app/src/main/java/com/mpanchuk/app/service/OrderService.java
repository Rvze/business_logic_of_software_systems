package com.mpanchuk.app.service;

import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final KafkaTemplate<String, Object> template;

    public void makeOrder(CreateOrderDto createOrderDto) {
        template.send("order", createOrderDto);
    }
}
