package com.mpanchuk.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityDistancePair<String, Integer> {
    private String cityName ;
    private Integer distance ;
}
