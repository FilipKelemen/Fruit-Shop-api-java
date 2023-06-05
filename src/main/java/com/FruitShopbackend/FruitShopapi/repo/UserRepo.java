package com.FruitShopbackend.FruitShopapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserEntity, String> {
    //this query only eagerly loads the data of user and cart essential data
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.carts c LEFT JOIN c.cartEntries WHERE u.email = :email")
    UserEntity fetchUserWithCartEagerly(@Param("email") String email);
}
