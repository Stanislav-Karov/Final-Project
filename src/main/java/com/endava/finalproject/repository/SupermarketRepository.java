package com.endava.finalproject.repository;

import com.endava.finalproject.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, String> {
    boolean existsByName(String name);
}
