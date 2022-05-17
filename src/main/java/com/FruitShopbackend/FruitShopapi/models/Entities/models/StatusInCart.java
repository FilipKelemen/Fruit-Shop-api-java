package com.FruitShopbackend.FruitShopapi.models.Entities.models;

public enum StatusInCart {

    ACTIVE("ACTIVE"),
    FINISHED("FINISHED");

    private final String statusInCart;

    StatusInCart(String statusInCart) {
        this.statusInCart = statusInCart;
    }

    public String getStatusInCart() {
        return statusInCart;
    }
}
