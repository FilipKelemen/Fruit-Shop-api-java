package com.FruitShopbackend.FruitShopapi.services.implementation;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.services.CartService;

@Service
public class CartServiceImpl implements CartService{

	private final CartRepo cartRepo;
	
	@Autowired
	public CartServiceImpl(CartRepo cartRepo){
		this.cartRepo = cartRepo;
	}

	@Override
	public Collection<Cart> getAll() {return this.cartRepo.findAll();}

	@Override
	public Cart getOne(UUID id) {
		Optional<Cart> cart = this.cartRepo.findById(id);
		if(cart.isEmpty())
			throw new NotFoundException("Cart with id "+id+" not found");
		return cart.get();
	}

	@Override
	public Cart post() {
		Cart cart =  new Cart();
		return this.cartRepo.save(cart);
	}

//	@Override
//	public Cart update(Cart cart) {
//		if(cart.getCartId() == null)
//			throw new NotAllFieldsCompletedException("No id provided");
//		UUID id = cart.getCartId();
//		Optional<Cart> cartFromDB = cartRepo.findById(id);
//		if(cartFromDB.isEmpty())
//			throw new NotFoundException("Cart with id "+id+" not found");
//		return this.cartRepo.save(cart);
//	}

	@Override
	public Boolean delete(UUID id) {
		Optional<Cart> cart = cartRepo.findById(id);
		if(cart.isEmpty())
			throw new NotFoundException("Cart with id "+id+" not found");
		this.cartRepo.deleteById(id);
		return true;
	}

}
