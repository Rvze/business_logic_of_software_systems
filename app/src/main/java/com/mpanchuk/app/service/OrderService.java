package com.mpanchuk.app.service;

import com.mpanchuk.app.domain.messaging.CreateOrderDto;
import com.mpanchuk.app.domain.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final KafkaTemplate<Long, CreateOrderDto> template;

    public void makeOrder(CreateOrderDto createOrderDto) {
        template.send("order-request", createOrderDto);
//        ProducerRecord<String, Object> record = new ProducerRecord<>("order-request", createOrderDto);
//        CompletableFuture<SendResult<String, Object>> future = template.send(record);
//        System.out.println(future);
    }

}
