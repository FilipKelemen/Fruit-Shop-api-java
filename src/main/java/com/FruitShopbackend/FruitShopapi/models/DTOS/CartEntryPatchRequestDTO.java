package com.FruitShopbackend.FruitShopapi.models.DTOS;

import javax.validation.constraints.NotNull;

public class CartEntryPatchRequestDTO {

    @NotNull(message = "The quantity should be present")
    private Integer quantity;

    public  CartEntryPatchRequestDTO() {}

    public CartEntryPatchRequestDTO(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartEntryPatchRequestDTO{" +
                "quantity=" + quantity +
                '}';
    }
}
