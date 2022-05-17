package com.FruitShopbackend.FruitShopapi.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
