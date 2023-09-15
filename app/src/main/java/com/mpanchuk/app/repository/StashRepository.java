package com.mpanchuk.app.repository;

import com.mpanchuk.app.exception.ItemToAddValidationException;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.model.Stash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class StashRepository {

    // username - list<stash_pair>>
    private final StashJpaRepository repository;
    private final ItemRepository itemRepository;

    public Stash addItem(String username, Item item, int amount) {
        Stash stash = repository.findByUsername(username);
        Integer price;
        if (stash == null) {
            price = 0;
            stash = Stash.builder().username(username).items(new ArrayList<>()).build();
            for (int i = 0; i < amount; i++) {
                stash.getItems().add(item);
                price += item.getPrice();
            }
        } else {
            price = stash.getPrice();
            for (int i = 0; i < amount; i++) {
                stash.getItems().add(item);
                price += item.getPrice();
            }
        }
        item.setStash(stash);
        itemRepository.save(item);
        stash.setPrice(price);
        repository.save(stash);
        return stash;
    }

    public Stash deleteItem(String username, Item item, int amount) {
        Stash stash = repository.findByUsername(username);
        if (stash == null) {
            throw new ItemToAddValidationException(String.format("Stash not found by username : %s", username));
        }
        Integer price = stash.getPrice();
        for (int i = 0; i < amount; i++) {
            price -= item.getPrice();
            stash.getItems().remove(item);
        }
        stash.setPrice(price);
        return stash;
    }
    public int calcPrice(String username) {
        Stash stash = repository.findByUsername(username);
        if (stash == null) {
            return 0;
        }

        return stash.getPrice();

    }
    public Stash getStorage(String username) {
        return repository.findByUsername(username);
    }
}
