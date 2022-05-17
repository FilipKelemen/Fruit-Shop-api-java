package com.FruitShopbackend.FruitShopapi.services;

import com.FruitShopbackend.FruitShopapi.models.DTOS.CartEntryPatchRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.CartEntry;
import com.FruitShopbackend.FruitShopapi.models.DTOS.CartEntryPostRequestDTO;

import java.util.UUID;

public interface CartEntryService {
    CartEntry post(CartEntryPostRequestDTO cartEntryPostRequestDTOTO, UUID id) throws Exception;
    CartEntry update(UUID cartId,UUID cartEntryId, CartEntryPatchRequestDTO cartEntryPatchRequestDTO) throws Exception;
    Boolean delete(UUID cartId, UUID cartEntryId) throws Exception;
}
