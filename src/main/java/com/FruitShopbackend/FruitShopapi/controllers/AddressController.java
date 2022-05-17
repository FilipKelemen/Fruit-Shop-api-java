package com.FruitShopbackend.FruitShopapi.controllers;

import com.FruitShopbackend.FruitShopapi.models.DTOS.AddressRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.AddressType;
import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/fruits/cart/{cartId}/address/{addressType}")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = {"","/"})
    public ResponseEntity<Response> postAddress(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("addressType") AddressType addressType,
            @Valid @RequestBody AddressRequestDTO addressFromRequest
    ) {
        String formattedAddressType = addressType.toString().substring(0,1).toUpperCase() + addressType.toString().substring(1);
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                formattedAddressType + " address added",
                null,
                Map.of(  "address", addressService.post(cartId, addressType, addressFromRequest))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @PatchMapping(path = {"","/"})
    public ResponseEntity<Response> updateCartEntry(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("addressType") AddressType addressType,
            @Valid @RequestBody AddressRequestDTO addressFromRequest
    )  {
        String formattedAddressType = addressType.toString().substring(0,1).toUpperCase() + addressType.toString().substring(1);
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                formattedAddressType + " address from cart "+ cartId +" updated",
                null,
                Map.of( "address", addressService.update(cartId, addressType, addressFromRequest))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @DeleteMapping(path = {"","/"})
    public ResponseEntity<Response> deleteCartEntry(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("addressType") AddressType addressType
    )  {
        String formattedAddressType = addressType.toString().substring(0,1).toUpperCase() + addressType.toString().substring(1);
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                formattedAddressType + " address from cart "+ cartId +" deleted" ,
                null,
                Map.of("deleted", addressService.delete(cartId,addressType))
        );
        return ResponseEntity.ok(
                response
        );
    }
}
