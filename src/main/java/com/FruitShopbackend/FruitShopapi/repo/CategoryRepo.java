package com.FruitShopbackend.FruitShopapi.repo;

import com.FruitShopbackend.FruitShopapi.models.Entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {

    Page<Category> findByName(String category, Pageable pageable);

}
