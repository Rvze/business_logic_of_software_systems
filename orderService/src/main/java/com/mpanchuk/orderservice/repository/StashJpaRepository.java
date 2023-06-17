package com.mpanchuk.orderservice.repository;

import com.mpanchuk.orderservice.model.Stash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StashJpaRepository extends JpaRepository<Stash, Long> {
    Stash findByUsername(String username);
}
