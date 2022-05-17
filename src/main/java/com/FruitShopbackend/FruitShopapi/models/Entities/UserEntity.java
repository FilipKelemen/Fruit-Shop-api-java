package com.FruitShopbackend.FruitShopapi.models.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue
	private UUID userId;
	@Column(unique = true)
	@NotEmpty( message = "Email should not be empty or null")
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private boolean admin;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	@JsonManagedReference
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

	
	public UserEntity() {}

	public UserEntity(UUID userId, @NotEmpty(message = "Email should not be empty or null") String email,
					  @NotNull String password, @NotNull String firstName, @NotNull String lastName, List<Address> addresses,
					  List<Cart> carts, boolean admin) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = addresses;
		this.carts = carts;
		this.admin = admin;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", admin=" + admin +
				", addresses=" + addresses +
				", carts=" + carts +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
