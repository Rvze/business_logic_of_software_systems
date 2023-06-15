package com.mpanchuk.app.domain.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class OrderRequest {
    @NotEmpty(message = "name is required")
    private String name ;
    @NotEmpty(message = "surname is required")
    private String surname ;
    @NotEmpty(message = "city is required")
    private String city ;
    @NotNull
    private String coupon ;
}
