package com.FruitShopbackend.FruitShopapi.models.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class UserEntity {

	@Id
	private UUID email;
	@NotNull
	private boolean admin;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	@JsonManagedReference
	@Size(max=2)
	@OneToMany(
		mappedBy = "userEntityInAddress",
		cascade = CascadeType.ALL,
        orphanRemoval = true
	)
	private List<Address> addresses = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(
		mappedBy = "userEntityInCart",
		cascade = CascadeType.ALL,
        orphanRemoval = true)
	private List<Cart> carts = new ArrayList<>();

	public UserEntity() {
	}

	public UserEntity(UUID email, boolean admin, LocalDateTime createdAt, LocalDateTime updatedAt, List<Address> addresses, List<Cart> carts) {
		this.email = email;
		this.admin = admin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.addresses = addresses;
		this.carts = carts;
	}

	public UUID getEmail() {
		return email;
	}

	public void setEmail(UUID gmail) {
		this.email = gmail;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"gmail=" + email +
				", admin=" + admin +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", addresses=" + addresses +
				", carts=" + carts +
				'}';
	}
}
