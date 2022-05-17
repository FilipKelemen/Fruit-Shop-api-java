package com.FruitShopbackend.FruitShopapi.controllers;

import com.FruitShopbackend.FruitShopapi.models.DTOS.CartEntryPatchRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.CartEntry;
import com.FruitShopbackend.FruitShopapi.models.DTOS.CartEntryPostRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.implementation.CartEntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/fruits/cart/{cartId}/cartEntry")
public class CartEntryController {

    private final CartEntryServiceImpl cartEntryServiceImpl;

    @Autowired
    CartEntryController(CartEntryServiceImpl cartEntryServiceImpl) {
        this.cartEntryServiceImpl = cartEntryServiceImpl;
    }

    @PostMapping(path = {"","/"})
    public ResponseEntity<Response> postCartEntry(
            @PathVariable("cartId") UUID cartId,
            @Valid @RequestBody CartEntryPostRequestDTO cartEntryPostRequestDTO
    ) throws Exception{
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart entry added",
                null,
                Map.of("cartEntry", cartEntryServiceImpl.post(cartEntryPostRequestDTO, cartId))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @PatchMapping(path = {"/{cartEntryId}","/{cartEntryId}/"})
    public ResponseEntity<Response> updateCartEntry(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("cartEntryId") UUID cartEntryId,
            @RequestBody @Valid CartEntryPatchRequestDTO cartEntryPatchRequestDTO
    ) throws Exception {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart entry with id"+ cartEntryId+"updated",
                null,
                Map.of("cartEntry", cartEntryServiceImpl.update(cartId, cartEntryId,cartEntryPatchRequestDTO))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @DeleteMapping(path = {"/{cartEntryId}","/{cartEntryId}/"})
    public ResponseEntity<Response> deleteCartEntry(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("cartEntryId") UUID cartEntryId
    ) throws Exception {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Cart entry"+cartEntryId+" deleted",
                null,
                Map.of("deleted", cartEntryServiceImpl.delete(cartId,cartEntryId))
        );
        return ResponseEntity.ok(
                response
        );
    }
}
