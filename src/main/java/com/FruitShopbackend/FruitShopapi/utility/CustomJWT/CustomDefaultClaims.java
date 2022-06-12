package com.FruitShopbackend.FruitShopapi.utility.CustomJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Map;

public class CustomDefaultClaims extends DefaultClaims implements CustomClaims {
    public CustomDefaultClaims() {
    }

    public CustomDefaultClaims(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getEmail() {
        return this.getString(EMAIL);
    }

    @Override
    public Claims setEmail(String email) {
        this.setValue(EMAIL, email);
        return this;
    }

}
