package com.mpanchuk.app.repository;

import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.model.Stash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StashJpaRepository extends JpaRepository<Stash, Long> {
    Stash findByUsername(String username);
}
