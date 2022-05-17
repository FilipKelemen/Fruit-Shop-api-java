package com.FruitShopbackend.FruitShopapi.models.Entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.FruitShopbackend.FruitShopapi.models.DTOS.AddressRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private UUID addressId;
	@NotNull
	private AddressType addressType;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotEmpty(message = "Email should not be empty or null")
	private String email;
	@NotNull
	private String phoneNumber;
	@NotNull
	private String completeStreet;
	@NotNull
	private String country;
	@NotNull
	private String state;
	@NotNull
	private String city;
	@NotNull
	private String postalCode;
	private String company;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cartInAddress;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity userEntityInAddress;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public Address() {
		super();
	}

	public Address(UUID addressId, AddressType addressType,  AddressRequestDTO addressRequestDTO,  Cart cartInAddress, UserEntity userEntityInAddress) {
		this.addressId = addressId;
		this.addressType = addressType;
		this.firstName = addressRequestDTO.getFirstName();
		this.lastName = addressRequestDTO.getLastName();
		this.email = addressRequestDTO.getEmail();
		this.phoneNumber = addressRequestDTO.getPhoneNumber();
		this.completeStreet = addressRequestDTO.getCompleteStreet();
		this.country = addressRequestDTO.getCountry();
		this.state = addressRequestDTO.getState();
		this.city = addressRequestDTO.getCity();
		this.postalCode = addressRequestDTO.getPostalCode();
		this.company = addressRequestDTO.getCompany();
		this.cartInAddress = cartInAddress;
		this.userEntityInAddress = userEntityInAddress;
	}

	public Address(UUID addressId, AddressType addressType, String firstName, String lastName,
				   String email, String phoneNumber, String completeStreet, String country, String state,
				   String city, String postalCode, String company, Cart cartInAddress, UserEntity userEntityInAddress,
				   LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.addressId = addressId;
		this.addressType = addressType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.completeStreet = completeStreet;
		this.country = country;
		this.state = state;
		this.city = city;
		this.postalCode = postalCode;
		this.company = company;
		this.cartInAddress = cartInAddress;
		this.userEntityInAddress = userEntityInAddress;
	}

	public UUID getAddressId() {
		return addressId;
	}

	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	public AddressType getType() {
		return addressType;
	}

	public void setType(AddressType addressType) {
		this.addressType = addressType;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompleteStreet() {
		return completeStreet;
	}

	public void setCompleteStreet(String completeStreet) {
		this.completeStreet = completeStreet;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Cart getCartInAddress() {
		return cartInAddress;
	}

	public void setCartInAddress(Cart cartInAddress) {
		this.cartInAddress = cartInAddress;
	}

	public UserEntity getUserEntityInAddress() {
		return userEntityInAddress;
	}

	public void setUserEntityInAddress(UserEntity userEntityInAddress) {
		this.userEntityInAddress = userEntityInAddress;
	}

	@Override
	public String toString() {
		return "Address{" +
				"addressId=" + addressId +
				", type=" + addressType +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", completeStreet='" + completeStreet + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", postalCode='" + postalCode + '\'' +
				", company='" + company + '\'' +
				'}';
	}
}
