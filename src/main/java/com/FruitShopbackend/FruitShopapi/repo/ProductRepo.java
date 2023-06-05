package com.FruitShopbackend.FruitShopapi.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.Product;

public interface ProductRepo extends JpaRepository<Product, UUID>{
	
	Optional<Product> findByName(String name);

}
