package com.FruitShopbackend.FruitShopapi.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.Address;

public interface AddressRepo extends JpaRepository<Address, UUID>{

}
