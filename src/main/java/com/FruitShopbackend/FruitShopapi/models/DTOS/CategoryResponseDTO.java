package com.FruitShopbackend.FruitShopapi.models.DTOS;

import com.FruitShopbackend.FruitShopapi.models.Entities.Category;
import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CategoryResponseDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Page<Product> products;

    public CategoryResponseDTO(Category category, Page<Product> products) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.products = products;
    }

    @Override
    public String toString() {
        return "CategoryResponseDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}
