package com.mpanchuk.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mpanchuk.app.domain.response.StashResponse;
import com.mpanchuk.app.mapper.CityMapper;
import com.mpanchuk.app.mapper.ItemMapper;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.model.Stash;
import com.mpanchuk.app.repository.ItemRepository;
import com.mpanchuk.app.repository.StashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StashService {
    private final ItemRepository itemRepository;
    private final StashRepository stashRepository;
    private final ItemMapper itemMapper;
    private final CityMapper cityMapper;

    public List<StashResponse> addItem(String jwt, Long itemId, int amount) throws NoSuchElementException, JsonProcessingException {
        Item item = itemRepository.findById(itemId).orElseThrow();
        var stash = stashRepository.addItem(jwt, item, amount);
        return mapper(stash);
    }

    public List<StashResponse> deleteItem(String username, Long itemId, int amount) throws NoSuchElementException {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return mapper(stashRepository.deleteItem(username, item, amount));
    }

    private List<StashResponse> mapper(Stash stash) {
        List<StashResponse> stashResponses = new ArrayList<>();
        if (stash == null)
            return List.of();
        stash.getItems().forEach(it ->
                stashResponses.add(StashResponse.builder()
                        .itemResponse(itemMapper.toResponse(it))
                        .cities(it.getCities().stream().map(cityMapper::toResponse).collect(Collectors.toList()))
                        .amount(stash.getItems().size())
                        .build()));
        return stashResponses;
    }

    public List<StashResponse> getStash(String username) {
        var stash = stashRepository.getStorage(username);
        return mapper(stash);
    }
}
