package com.FruitShopbackend.FruitShopapi.repo;

import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserEntity, String> {

    @Query("SELECT u FROM UserEntity u LEFT JOIN u.carts c WHERE u.email = :email")
    UserEntity fetchUserEagerly(@Param("email") String email);
}
