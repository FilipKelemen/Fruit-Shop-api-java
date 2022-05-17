package com.FruitShopbackend.FruitShopapi.repo;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, UUID>{
	
	Optional<Product> findByName(String name);

	//TODO test this I have no idea if it works
//	@Query("SELECT p" +
//			"FROM PRODUCT p LEFT JOIN p.color col" +
//			"where p.product_id = col.product_id AND col.name = col.:colorName")
//	List<Product> findAllByColor(@Param("colorName") String colorName, Pageable pageable);
}
