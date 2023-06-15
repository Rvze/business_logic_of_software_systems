package com.mpanchuk.app.controller;

import com.mpanchuk.app.domain.request.ItemToAddRequest;
import com.mpanchuk.app.domain.response.ItemResponse;
import com.mpanchuk.app.domain.response.ItemToAddResponse;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController implements SecuredRestController{

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public Page<ItemResponse> getAllItems(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return itemService.getAllItems(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ItemResponse itemResponse = new ItemResponse(item.getId(), item.getName(), item.getPrice());

        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
    }

    @GetMapping("/find")
    public Page<ItemResponse> getItemByName(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String regexp) {
        logger.info("Getting item with regexp: " + regexp);
        return itemService.getItemByRegexp(regexp, pageNo, pageSize);
    }

    @PostMapping("/supplier")
    public ResponseEntity<Void> addItem(@RequestBody List<ItemToAddRequest> request) {
        itemService.addItemFromSupplier(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager")
    public ResponseEntity<Page<ItemToAddResponse>> getItem(
            @RequestParam(defaultValue = "0")
            Integer pageNumber,
            @RequestParam(defaultValue = "10")
            Integer pageSize
    ) {
        var response = itemService.getItemsToAdd(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/manager")
    public ResponseEntity<List<ItemResponse>> addItemsFromManager(
            @RequestBody List<Long> ids
    ) {
        var response = itemService.addItemsFromManager(ids);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
