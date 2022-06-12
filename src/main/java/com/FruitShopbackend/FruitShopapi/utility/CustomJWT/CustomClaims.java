package com.FruitShopbackend.FruitShopapi.utility.CustomJWT;

import io.jsonwebtoken.Claims;

public interface CustomClaims extends Claims {
    String EMAIL = "email";

    String getEmail();

    Claims setEmail(String email);

}
