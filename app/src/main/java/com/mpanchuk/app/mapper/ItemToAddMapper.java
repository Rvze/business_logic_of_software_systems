package com.mpanchuk.app.mapper;

import com.mpanchuk.app.domain.request.ItemToAddRequest;
import com.mpanchuk.app.domain.response.ItemToAddResponse;
import com.mpanchuk.app.model.City;
import com.mpanchuk.app.model.ItemToAdd;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ItemToAddMapper {
    public ItemToAdd toEntity(ItemToAddRequest item, Set<City> cities) {
        return ItemToAdd.builder()
                .price(item.price())
                .name(item.name())
                .cities(cities)
                .build();
    }

    public ItemToAddResponse toResponse(ItemToAdd item) {
        return ItemToAddResponse.builder()
                .id(item.getId())
                .city(item.getCities().stream().map(City::getName).collect(Collectors.toSet()))
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }
}
