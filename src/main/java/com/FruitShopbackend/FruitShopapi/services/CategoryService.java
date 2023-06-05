package com.FruitShopbackend.FruitShopapi.services;

import com.FruitShopbackend.FruitShopapi.models.DTOS.CategoryResponseDTO;
import com.FruitShopbackend.FruitShopapi.services.implementation.models.SortingMode;

public interface CategoryService {

    CategoryResponseDTO getPageAndNumberOfPagesOfCategory(String category, Integer pageNumber, SortingMode sortingMode);

}
