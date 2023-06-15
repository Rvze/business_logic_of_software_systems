package com.mpanchuk.app.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemResponse {
    private Long id;
    private String name;
    private int price;
}
