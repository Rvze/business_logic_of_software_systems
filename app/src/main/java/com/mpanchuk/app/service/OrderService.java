package com.mpanchuk.app.service;

import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final KafkaTemplate<String, CreateOrderDto> template;

    public void makeOrder(CreateOrderDto createOrderDto) {
        UUID uuid = UUID.randomUUID();
        createOrderDto.setUuid(uuid);
        CompletableFuture<SendResult<String, CreateOrderDto>> future = template.send("order-request", createOrderDto);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + createOrderDto +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        createOrderDto + "] due to : " + ex.getMessage());
            }
        });
    }

//    @KafkaListener(topics = "order-request", groupId = "orders")
//    public void listen(CreateOrderDto createOrderDto) {
//        System.out.println("Message : " + createOrderDto);
//    }


}
