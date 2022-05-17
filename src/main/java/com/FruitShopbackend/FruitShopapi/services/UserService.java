package com.FruitShopbackend.FruitShopapi.services;

import com.FruitShopbackend.FruitShopapi.models.DTOS.LoginUserDTO;
import com.FruitShopbackend.FruitShopapi.models.DTOS.RegisterUserDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;

import java.util.Map;

public interface UserService {
    //"cart": cart "jwt": jwt
    Map<String, ?> saveUser(RegisterUserDTO user);
    Map<String, ?> authenticateUser(LoginUserDTO userFromRequest);
}
