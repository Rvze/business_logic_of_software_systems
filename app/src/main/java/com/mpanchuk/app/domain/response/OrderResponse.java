package com.mpanchuk.app.domain.response;

import com.mpanchuk.app.domain.CityDistancePair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderResponse {
    private HashMap<String, CityDistancePair<String, Integer>> itemToCity ;
    private String destination ;
    private int price ;
    private int discount ;
    private int totalPrice ;
    private String message ;
}
