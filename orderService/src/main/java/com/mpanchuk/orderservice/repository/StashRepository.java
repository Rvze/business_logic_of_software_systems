package com.mpanchuk.orderservice.repository;//package com.mpanchuk.orderservice.repository;

import com.mpanchuk.orderservice.model.Item;
import com.mpanchuk.orderservice.model.Stash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StashRepository {

    // username - list<stash_pair>>
    private final StashJpaRepository repository;

    public Stash getStorage(String username) {
        return repository.findByUsername(username);
    }

    public int calcPrice(String username) {
        Stash stash = repository.findByUsername(username);
        if (stash == null) {
            return 0;
        }

        return stash.getPrice();

    }
}
