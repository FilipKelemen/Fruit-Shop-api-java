package com.FruitShopbackend.FruitShopapi.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.Color;

public interface ColorRepo  extends JpaRepository<Color, UUID>{

	Color findByName(String name);
}
