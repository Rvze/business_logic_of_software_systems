package com.mpanchuk.app.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ItemToAddRequest(
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "price")
        Integer price,
        @JsonProperty(value = "city")
        List<String> city
) {
}
