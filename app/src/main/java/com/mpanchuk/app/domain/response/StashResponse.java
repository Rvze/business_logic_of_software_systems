package com.mpanchuk.app.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class StashResponse {
    private ItemResponse itemResponse;
    private Integer amount;
    private List<CityResponse> cities;
}
