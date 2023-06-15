package com.mpanchuk.app.domain.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemToAddResponse {
    private Long id;
    private String name;
    private int price;
    private Set<String> city;
}