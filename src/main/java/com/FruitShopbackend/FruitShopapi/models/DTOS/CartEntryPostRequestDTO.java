package com.FruitShopbackend.FruitShopapi.models.DTOS;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CartEntryPostRequestDTO {

    @NotNull(message = "The quantity should not be null")
    private int quantity;

    @NotNull(message = "The product id should not be null")
    private UUID productId;

    public CartEntryPostRequestDTO() {}

    public CartEntryPostRequestDTO(int quantity, UUID productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartEntryPostRequestDTO{" +
                "quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }

}
