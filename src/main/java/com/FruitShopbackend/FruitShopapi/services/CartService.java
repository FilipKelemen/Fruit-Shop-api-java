package com.FruitShopbackend.FruitShopapi.services;

import java.util.Collection;
import java.util.UUID;

import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;

public interface CartService {
	Collection<Cart> getAll();
	Cart getOne(UUID id);
	Cart post();
//	Cart update(Cart cart);
	Boolean delete(UUID id);
}
