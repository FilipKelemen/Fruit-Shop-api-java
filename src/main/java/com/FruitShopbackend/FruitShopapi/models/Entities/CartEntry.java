package com.FruitShopbackend.FruitShopapi.models.Entities;


import com.FruitShopbackend.FruitShopapi.utility.PriceUtility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CartEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cartEntryId;

	@NotNull
	private int quantity;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cart_id")
    private Cart cartInEntry;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "product_id")
    private Product product;

	public CartEntry() {}

	//clone constructor
	public CartEntry(CartEntry cartEntryToBeCloned) {
		this(
			cartEntryToBeCloned.getCartEntryId(),
			cartEntryToBeCloned.quantity,
			cartEntryToBeCloned.getCartInEntry(),
			new Product(
					cartEntryToBeCloned.getProduct().getProductId(),
					cartEntryToBeCloned.getProduct().getName(),
					cartEntryToBeCloned.getProduct().getColors(),
					cartEntryToBeCloned.getProduct().getNumberInStock(),
					cartEntryToBeCloned.getProduct().getImageUrl(),
					cartEntryToBeCloned.getProduct().getPriceValue(),
					cartEntryToBeCloned.getProduct().getCurrency(),
					cartEntryToBeCloned.getProduct().getDescription()
			)
		);
	}

	public CartEntry(UUID cartEntryId, @NotNull int quantity, Cart cartInEntry, Product product) {
		this.cartEntryId = cartEntryId;
		this.quantity = quantity;
		this.cartInEntry = cartInEntry;
		this.product = product;
	}

	public UUID getCartEntryId() {
		return cartEntryId;
	}

	public void setCartEntryId(UUID cartEntryId) {
		this.cartEntryId = cartEntryId;
	}

	public String getFormattedTotal() {
		try {
			return PriceUtility.getFormattedPrice(product.getPriceValue() * quantity,"$");
		} catch (Exception exception) {
			return exception.getMessage();
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCartInEntry() {
		return cartInEntry;
	}

	public void setCartInEntry(Cart cartInEntry) {
		this.cartInEntry = cartInEntry;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartEntry{" +
				"cartEntryId=" + cartEntryId +
				", quantity=" + quantity +
				", product=" + product +
				", formattedPrice='" + getFormattedTotal() + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
