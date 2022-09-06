package com.FruitShopbackend.FruitShopapi.controllers;

import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.implementation.UserServiceImpl;
import com.FruitShopbackend.FruitShopapi.utility.AuthTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path = "/fruits/user/cart")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path = "")
    public ResponseEntity<Response> obtainOrCreateCartOfUser(JwtAuthenticationToken token) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart retrieved",
                null,
                Map.of("cart", userService.createOrObtainCartFromUser(token))
        );
        return ResponseEntity.ok(
                response
        );
    }
}
