package com.FruitShopbackend.FruitShopapi.controllers;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.models.DTOS.LoginUserDTO;
import com.FruitShopbackend.FruitShopapi.models.DTOS.MyUserWithCart;
import com.FruitShopbackend.FruitShopapi.models.DTOS.RegisterUserDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.implementation.UserServiceImpl;
import com.FruitShopbackend.FruitShopapi.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path="/fruits/authorization")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = {"/register", "/register/"})
    public ResponseEntity<Response> registerUser(
            @Valid @RequestBody RegisterUserDTO user
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "User registered",
                null,
                userService.saveUser(user)
        );
        return ResponseEntity.ok(
                response
        );
    }

    //TODO try extracting in the service
    @PostMapping(path={"/authenticate","/authenticate/"})
    public ResponseEntity<Response> authenticateUser(
            @Valid @RequestBody LoginUserDTO userFromRequest
    ) throws BadCredentialsException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userFromRequest.getEmail(),userFromRequest.getPassword())
            );
        } catch (BadCredentialsException exception){
            throw new ProblemWithFieldsException("Incorrect username and password");
        }

        //loadUserByUsername returns my custom user details that has a cart attached I did this in order to not be put in a situation
        //where I need to query the database again
        final MyUserWithCart userDetails = (MyUserWithCart)userService.loadUserByUsername(userFromRequest.getEmail());

        final String jwt = jwtUtility.generateToken(userDetails);

        final Cart cart = userDetails.getCart();

        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "User authenticated",
                null,
                Map.of("jwt",jwt, "cart", cart)
        );
        return ResponseEntity.ok(
                response
        );
    }
}
