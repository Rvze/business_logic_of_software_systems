package com.mpanchuk.app.domain.messaging;

import lombok.Data;

@Data
public class CreateOrderDto {
    private String username;
    private String destination;
    private String coupon;
}
