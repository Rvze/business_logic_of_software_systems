package com.mpanchuk.orderservice.repository;

import com.mpanchuk.orderservice.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {
}
