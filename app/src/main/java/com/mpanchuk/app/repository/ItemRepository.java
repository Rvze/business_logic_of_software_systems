package com.mpanchuk.app.repository;

import com.mpanchuk.app.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);

    @Query("SELECT i FROM Item i WHERE lower(i.name) LIKE lower(concat('%', :regexp, '%'))")
    Optional<Page<Item>> findByNameLike(@Param("regexp") String regexp, Pageable pageable);
}
