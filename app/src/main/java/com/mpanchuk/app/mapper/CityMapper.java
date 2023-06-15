package com.mpanchuk.app.mapper;

import com.mpanchuk.app.domain.response.CityResponse;
import com.mpanchuk.app.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public CityResponse toResponse(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
