package com.mpanchuk.app.service;

import com.mpanchuk.app.config.TransactionManagerChecker;
import com.mpanchuk.app.domain.request.ItemToAddRequest;
import com.mpanchuk.app.domain.response.ItemResponse;
import com.mpanchuk.app.domain.response.ItemToAddResponse;
import com.mpanchuk.app.exception.ItemToAddValidationException;
import com.mpanchuk.app.exception.NoSuchItemException;
import com.mpanchuk.app.mapper.ItemMapper;
import com.mpanchuk.app.mapper.ItemToAddMapper;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.repository.CityRepository;
import com.mpanchuk.app.repository.ItemRepository;
import com.mpanchuk.app.repository.ItemToAddRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemToAddRepository itemToAddRepository;
    private final ItemToAddMapper itemToAddMapper;
    private final CityRepository cityRepository;
    private final ItemMapper mapper;
    private final TransactionManagerChecker transactionManagerChecker;

    public Page<ItemResponse> getAllItems(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Item> entityItems = itemRepository.findAll(pageable);
        return entityItems.map(item -> new ItemResponse(item.getId(), item.getName(), item.getPrice()));
    }

    public Page<ItemResponse> getItemByRegexp(String regexp, int pageNo, int pageSize) {
        Page<Item> entityItems = itemRepository.findByNameLike(regexp, PageRequest.of(pageNo, pageSize)).orElseThrow();
        return entityItems.map(item -> new ItemResponse(item.getId(), item.getName(), item.getPrice()));
    }

    public Item getItemById(Long id) throws NoSuchElementException {
        return itemRepository.findById(id).orElseThrow();
    }

    /**
     * SUPPLIER
     */
    @Transactional
    public void addItemFromSupplier(List<ItemToAddRequest> request) {
        System.out.println(transactionManagerChecker.getTransactionManagerType());
        request.forEach(it -> {
            validateItemToAddRequest(it);
            var cities = cityRepository.findAllByNameIn(it.city());
            var entity = itemToAddMapper.toEntity(it, new HashSet<>(cities));
            itemToAddRepository.save(entity);
        });
    }


    /**
     * MANAGER
     */
    public Page<ItemToAddResponse> getItemsToAdd(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        var itemsToAdd = itemToAddRepository.findAll(pageable);
        return itemsToAdd.map(itemToAddMapper::toResponse);
    }

    /**
     * MANAGER
     */
    @Transactional
    public List<ItemResponse> addItemsFromManager(List<Long> ids) {
        List<ItemResponse> itemResponses = new ArrayList<>();
        ids.forEach(it -> {
            var itemToAdd = itemToAddRepository.findById(it).orElseThrow(
                    () -> new NoSuchItemException(String.format("Item to add не сущетвует: %s", it)));
            var item = mapper.toEntity(itemToAdd);
            itemToAddRepository.delete(itemToAdd);
            ItemResponse itemResponse = mapper.toResponse(itemRepository.save(item));
            itemResponses.add(itemResponse);
        });
        return itemResponses;

    }


    private void validateItemToAddRequest(ItemToAddRequest request) {
        request.city().forEach(it -> cityRepository.findByName(it).orElseThrow(
                () -> new ItemToAddValidationException(String.format("Города %s не существует!", request.city()))));
        if (request.price() < 0)
            throw new ItemToAddValidationException(String.format("Цена не может быть меньше 0! %s", request.price()));
        if (request.name() == null || request.name().equals(""))
            throw new ItemToAddValidationException("Название товара не может быть пустым!");
    }
}
