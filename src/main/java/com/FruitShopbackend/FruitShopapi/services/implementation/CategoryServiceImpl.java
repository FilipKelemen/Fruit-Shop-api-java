package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.NotFoundException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.models.DTOS.CategoryResponseDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Category;
import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.repo.CategoryRepo;
import com.FruitShopbackend.FruitShopapi.services.CategoryService;
import com.FruitShopbackend.FruitShopapi.services.implementation.models.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryResponseDTO getPageAndNumberOfPagesOfCategory(String category, Integer pageNumber, SortingMode sortingMode) {
        if(pageNumber < 1)
            throw new ProblemWithFieldsException("The page number is below 1");
        final Pageable sortedPage = sortingMode.getPageWithSortingSpecified(pageNumber);
        Optional<Category> optionalCategory  = categoryRepo.findById(category);
        if(optionalCategory.isEmpty())
            throw new NotFoundException("Category "+ category +" not found");
        Set<Product> products = optionalCategory.get().getProducts();
        Page<Product> pagedProducts = new PageImpl(new ArrayList(products),sortedPage,products.size());
        System.out.println(new CategoryResponseDTO(optionalCategory.get(),pagedProducts));
        return new CategoryResponseDTO(optionalCategory.get(),pagedProducts);
    }

}
