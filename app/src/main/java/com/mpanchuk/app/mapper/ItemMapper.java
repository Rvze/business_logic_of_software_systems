package com.mpanchuk.app.mapper;

import com.mpanchuk.app.domain.response.ItemResponse;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.model.ItemToAdd;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toEntity(ItemToAdd item) {
        return Item.builder()
                .name(item.getName())
                .price(item.getPrice())
                .cities(item.getCities())
                .build();
    }

    public ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
