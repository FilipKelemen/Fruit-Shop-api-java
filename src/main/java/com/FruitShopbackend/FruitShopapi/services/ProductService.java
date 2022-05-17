package com.FruitShopbackend.FruitShopapi.services;

import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.services.implementation.models.SortingMode;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Page<Product> getPageAndNumberOfPages(Integer pageNumber, SortingMode sortingMode);
    List<Product> getPage(Integer pageNumber, SortingMode sortingMode);
    Product getOne(String productId);
    Product post(Product product);
    Product update(Product product);
    Boolean delete(UUID productId);
}
