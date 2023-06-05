package com.FruitShopbackend.FruitShopapi.controllers;

import com.FruitShopbackend.FruitShopapi.models.responses.Response;
import com.FruitShopbackend.FruitShopapi.services.implementation.CategoryServiceImpl;
import com.FruitShopbackend.FruitShopapi.services.implementation.models.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path = "/fruits/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = {"/{category}/{pageNumber}/sorted-by-{sortingMode}"})
    public ResponseEntity<Response> getPageAndNumberOfPages(
            @PathVariable("category") String categoryName,
            @PathVariable("pageNumber") Integer pageNumber,
            @PathVariable("sortingMode") SortingMode sortingMode
    ) {
        Response response = new Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                null,
                "Products for category "+categoryName+" page "+pageNumber+" sorted by "+sortingMode+ " retrieved",
                null,
                Map.of("categories", categoryService.getPageAndNumberOfPagesOfCategory(categoryName,pageNumber,sortingMode))
        );
        return ResponseEntity.ok(
                response
        );
    }
}
