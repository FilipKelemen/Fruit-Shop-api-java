package com.FruitShopbackend.FruitShopapi.controllers;


import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.implementation.ProductServiceImpl;
import com.FruitShopbackend.FruitShopapi.services.implementation.models.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path="/fruits/product")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping(path = {"/page-and-number-of-pages-{pageNumber}/sortedBy-{sortingMode}","/page-and-number-of-pages-{pageNumber}/sortedBy-{sortingMode}/"})
    public ResponseEntity<Response> getPageAndNumberOfPages(
            @PathVariable("pageNumber") Integer pageNumber,
            @PathVariable("sortingMode") SortingMode sortingMode
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Products for page "+pageNumber+" and number of pages retrieved",
                null,
                Map.of("products", productServiceImpl.getPageAndNumberOfPages(pageNumber, sortingMode))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping(path = {"/page-{pageNumber}/sortedBy-{sortingMode}","/page-{pageNumber}/sortedBy-{sortingMode}/"})
    public ResponseEntity<Response> getPage(
            @PathVariable("pageNumber") Integer pageNumber,
            @PathVariable("sortingMode") SortingMode sortingMode
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Products for page "+pageNumber+" retrieved",
                null,
                Map.of("products", productServiceImpl.getPage(pageNumber, sortingMode))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping(path = {"/{productName}","/{productName}/"})
    public ResponseEntity<Response> getOneProduct(
            @PathVariable("productName") String productName
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Product "+productName+" retrieved",
                null,
                Map.of("product", productServiceImpl.getOne(productName))
        );
        return ResponseEntity.ok(
                response
        );
    }

    @GetMapping(path="/image/{imageName}", produces= MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(
            @PathVariable("imageName") String imageName
    ) throws IOException {
        return Files.readAllBytes(Paths.get(
                System.getProperty("user.home") +
                        "/Documents/PersonalProjects/Fruit-Shop-api-java/src/main/resources/Images/" +
                        imageName
        ));
    }

    @PostMapping(path = {"","/"})
    public ResponseEntity<Response> postProduct(
           @RequestBody Product product
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Product added",
                null,
                Map.of("product", productServiceImpl.post(product))
        );
        return ResponseEntity.ok(
                response
        );
    }

}
