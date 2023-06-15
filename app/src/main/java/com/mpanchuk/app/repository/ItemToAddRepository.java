package com.mpanchuk.app.repository;

import com.mpanchuk.app.model.ItemToAdd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemToAddRepository extends JpaRepository<ItemToAdd, Long> {
}
