package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.AlreadyExistsException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.NotFoundException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.repo.ProductRepo;
import com.FruitShopbackend.FruitShopapi.services.ProductService;
import com.FruitShopbackend.FruitShopapi.services.implementation.models.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Autowired
    ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Page<Product> getPageAndNumberOfPages(Integer pageNumber, SortingMode sortingMode) {
        System.out.println(sortingMode);
        if(pageNumber < 1)
            throw new ProblemWithFieldsException("The page number is below 1");
        Pageable sortedPage = sortingMode.getPageWithSortingSpecified(pageNumber);
        return productRepo.findAll(sortedPage);
    }

    @Override
    public List<Product> getPage(Integer pageNumber, SortingMode sortingMode) {
        if(pageNumber < 1)
            throw new ProblemWithFieldsException("The page number is below 1");
        //Page number starts at 0
        Pageable sortedPage = sortingMode.getPageWithSortingSpecified(pageNumber);
        return productRepo.findAll(sortedPage).getContent();
    }

    @Override
    public Product getOne(String productName) {
        Optional<Product> product= productRepo.findByName(productName);
        if(product.isEmpty())
            throw new NotFoundException("Product "+productName+" not found");
        return product.get();
    }

    //not tested
    @Override
    public Product post(Product product) {
        String name = product.getName();
        if(     name.isBlank()                      ||
                product.getNumberInStock() == null  ||
                product.getImageUrl().isBlank()     ||
                product.getPriceValue() == 0     ||
                product.getCurrency().isBlank()     ||
                product.getDescription().isBlank()
        )
            throw new ProblemWithFieldsException("Not all fields required are completed");
        Optional<Product> productFromDB = productRepo.findByName(name);
        if(productFromDB.isPresent())
            throw new AlreadyExistsException("The product with name "+name+" already exists");
        return productRepo.save(product);
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }
}
