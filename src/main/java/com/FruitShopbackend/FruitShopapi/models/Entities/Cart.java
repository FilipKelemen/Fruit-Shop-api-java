package com.FruitShopbackend.FruitShopapi.models.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.FruitShopbackend.FruitShopapi.models.Entities.models.AddressType;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.StatusInCart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@JsonIgnoreProperties("addresses")
public class Cart {
	
	@Id
	@GeneratedValue
	private UUID cartId;

	@NotNull
	private Long total;

	private String paymentMethod;

	@NotNull
	private StatusInCart status;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@JsonManagedReference
	@OneToMany(
		fetch = FetchType.LAZY,
		mappedBy = "cartInEntry",
		cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private List<CartEntry> cartEntries = new ArrayList<>();
	@JsonManagedReference
	@Size(max=2)
	@OneToMany(
		fetch = FetchType.LAZY,
		mappedBy = "cartInAddress",
		cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private List<Address> addresses = new ArrayList<>();

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "email")
	private UserEntity userEntityInCart;

	public Cart() {
		this.total = 0L;
		this.status = StatusInCart.ACTIVE;
	}
	public Cart(final UserEntity userEntityInCart) {
		this.total = 0L;
		this.status = StatusInCart.ACTIVE;
		this.userEntityInCart = userEntityInCart;
	}

	public Cart(UUID cartId, @NotNull Long total, @NotNull String paymentMethod,StatusInCart status,
			List<CartEntry> cartEntry, List<Address> addresses, UserEntity userEntityInCart) throws Exception {
		validateAddresses(addresses);
		this.cartId = cartId;
		this.total = total;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.cartEntries = cartEntry;
		this.addresses = addresses;
		this.userEntityInCart = userEntityInCart;
	}

	public UUID getCartId() {
		return cartId;
	}

	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<CartEntry> getCartEntries() {
		return cartEntries;
	}

	public void setCartEntries(List<CartEntry> cartEntries) {
		this.cartEntries = cartEntries;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public Address getBillingAddress() {
		return this.addresses.stream().filter(address -> address.getType().equals(AddressType.billing)).findFirst()
				.orElse(null);
	}

	public Address getDeliveryAddress() {
		return this.addresses.stream().filter(address -> address.getType().equals(AddressType.delivery)).findFirst()
				.orElse(null);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setBillingAddress(final Address address) {
		final Optional<Address> optionalAddress = this.addresses.stream().filter(persistedAddress -> persistedAddress.getType().equals(AddressType.billing)).findFirst();
		if(optionalAddress.isEmpty()) {
			this.addresses.add(address);
		} else {
			this.addresses = this.addresses.stream()
					.map(persistedAddress ->
							persistedAddress.getAddressId() != optionalAddress.get().getAddressId()
									? persistedAddress
									: address)
					.collect(Collectors.toList());
		}
	}

	public void setDeliveryAddress(final Address address) {
		final Optional<Address> optionalAddress = this.addresses.stream().filter(persistedAddress -> persistedAddress.getType().equals(AddressType.delivery)).findFirst();
		if(optionalAddress.isEmpty()) {
			this.addresses.add(address);
		} else {
			this.addresses = this.addresses.stream()
					.map(persistedAddress ->
							persistedAddress.getAddressId() != optionalAddress.get().getAddressId()
									? persistedAddress
									: address)
					.collect(Collectors.toList());
		}
	}

	public UserEntity getUserEntityInCart() {
		return userEntityInCart;
	}

	public void setUserEntityInCart(UserEntity userEntityInCart) {
		this.userEntityInCart = userEntityInCart;
	}

	public StatusInCart getStatus() {
		return status;
	}

	public void setStatus(StatusInCart status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart{" +
				"cartId=" + cartId +
				", paymentMethod='" + paymentMethod + '\'' +
				", status=" + status +
				", cartEntries=" + cartEntries +
				'}';
	}

	private void validateAddresses(final List<Address> addresses) throws Exception {
		final long numberOfBillingAddresses = addresses.stream().filter(address -> address.getType() == AddressType.billing).count();
		final long numberOfDeliveryAddresses = addresses.stream().filter(address -> address.getType() == AddressType.delivery).count();
		if(numberOfBillingAddresses!=1 || numberOfDeliveryAddresses!=1) {
				throw new Exception("Too many addresses of the same type");
		}
	}

	public void updateTotalWithExistingEntry(CartEntry updatedCartEntry) throws Exception {
		long tempTotal = total;
		for(CartEntry cartEntryTemp : cartEntries) {
			if(cartEntryTemp.getCartEntryId() == updatedCartEntry.getCartEntryId()){
				tempTotal -= cartEntryTemp.getQuantity() * cartEntryTemp.getProduct().getPriceValue();
				tempTotal += updatedCartEntry.getQuantity() * updatedCartEntry.getProduct().getPriceValue();
				//Extra layer of security
				if( tempTotal < 0 ){
					throw new Exception("Something went wrong with calculating the total");
				}
				break;
			}
		}

		total = tempTotal;
	}

	public void updateTotalWithNewEntry(CartEntry newCartEntry) throws Exception {
		long tempTotal = total;
		tempTotal += newCartEntry.getQuantity() * newCartEntry.getProduct().getPriceValue();
		//Extra layer of security
		if( tempTotal < 0 ){
			throw new Exception("Something went wrong with calculating the total");
		}
		total = tempTotal;
	}

	public void updateTotalWithDeletedEntry(CartEntry deletedCartEntry) throws Exception {
		long tempTotal = total;
		tempTotal -= deletedCartEntry.getQuantity() * deletedCartEntry.getProduct().getPriceValue();
		//Extra layer of security
		if( tempTotal < 0 ){
			throw new Exception("Something went wrong with calculating the total");
		}
		total = tempTotal;
	}
}


