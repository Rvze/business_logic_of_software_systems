package com.mpanchuk.app.domain.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("coupon")
    private String coupon;
    @JsonProperty("uuid")
    private UUID uuid;
}
