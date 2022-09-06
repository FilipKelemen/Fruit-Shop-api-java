package com.FruitShopbackend.FruitShopapi.services;

import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

public interface UserService {
    Cart createOrObtainCartFromUser(JwtAuthenticationToken token);
}
