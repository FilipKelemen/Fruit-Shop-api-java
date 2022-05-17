package com.FruitShopbackend.FruitShopapi.services;

import com.FruitShopbackend.FruitShopapi.models.DTOS.AddressRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Address;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.AddressType;

import java.util.UUID;

public interface AddressService {
    Address post(UUID cartId, AddressType addressType, AddressRequestDTO addressFromRequest);
    Address update(UUID cartId, AddressType addressType, AddressRequestDTO addressFromRequest);
    Boolean delete(UUID cartId, AddressType addressType);
}
