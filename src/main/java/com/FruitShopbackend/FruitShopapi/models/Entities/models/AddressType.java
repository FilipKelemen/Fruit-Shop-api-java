package com.FruitShopbackend.FruitShopapi.models.Entities.models;

public enum AddressType {

	BILLING("BILLING"),
	DELIVERY("DELIVERY");
	
	private final String addressType;
	
	AddressType(String addressType) {
		this.addressType = addressType;
	}
	
	public String getAddressType() {
		return this.addressType;
	}
	
}
