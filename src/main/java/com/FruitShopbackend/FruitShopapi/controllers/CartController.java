package com.FruitShopbackend.FruitShopapi.controllers;

import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.implementation.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/fruits/cart", consumes = MediaType.APPLICATION_JSON_VALUE)
public class CartController {
    private final CartServiceImpl cartServiceImpl;

    @Autowired
    CartController(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping(path = {"","/"})
    public ResponseEntity<Response> getCarts() {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Carts retrieved",
                null,
                Map.of("carts", cartServiceImpl.getAll())
        );
        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping(path = "/{cartId}")
    public ResponseEntity<Response> getCart(
            @PathVariable("cartId") UUID cartId
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart retrieved",
                null,
                Map.of("cart", cartServiceImpl.getOne(cartId))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @PostMapping(path = {"","/"})
    public ResponseEntity<Response> postCart(
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart added",
                null,
                Map.of("cart", cartServiceImpl.post())
        );
        return ResponseEntity.ok(
                response
        );
    }

//    @PutMapping(path = "/")
//    public ResponseEntity<Response> updateCart(
//            @RequestBody @Valid Cart cart
//    ) {
//        Response response = new Response(
//                LocalDateTime.now(),
//                HttpStatus.OK.value(),
//                HttpStatus.OK,
//                null,
//                "Cart updated",
//                null,
//                Map.of("cart", cartServiceImpl.update(cart))
//        );
//        return ResponseEntity.ok(
//                response
//        );
//    }

    @DeleteMapping(path = "/{cartId}")
    public ResponseEntity<Response> deleteCart(
            @PathVariable("cartId")UUID cartId
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart deleted",
                null,
                Map.of("deleted", cartServiceImpl.delete(cartId))
        );
        return ResponseEntity.ok(
                response
        );
    }


}
