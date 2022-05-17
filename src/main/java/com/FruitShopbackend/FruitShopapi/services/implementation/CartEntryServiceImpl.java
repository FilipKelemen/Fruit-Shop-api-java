package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.AlreadyExistsException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.NotFoundException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.models.DTOS.CartEntryPatchRequestDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.CartEntry;
import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.models.DTOS.CartEntryPostRequestDTO;
import com.FruitShopbackend.FruitShopapi.repo.CartEntryRepo;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.repo.ProductRepo;
import com.FruitShopbackend.FruitShopapi.services.CartEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartEntryServiceImpl implements CartEntryService {

    private final CartEntryRepo cartEntryRepo;
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    @Autowired
    public CartEntryServiceImpl(CartEntryRepo cartEntryRepo,
                                CartRepo cartRepo,
                                ProductRepo productRepo) {
        this.cartEntryRepo = cartEntryRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }


    @Override
    public CartEntry post(CartEntryPostRequestDTO cartEntryPostRequestDTO, UUID cartId)  throws Exception{
        // This method looks big but there are only validations here
        int quantity = cartEntryPostRequestDTO.getQuantity();
        if( quantity <= 0 )
            throw new ProblemWithFieldsException("The quantity should not be below 1");

        Optional<Cart> optionalCart = cartRepo.findById(cartId);
        if(optionalCart.isEmpty())
            throw new NotFoundException("Cart with id "+cartId+" not found while trying to set a product for the cart entry");

        UUID productId = cartEntryPostRequestDTO.getProductId();
        Optional<Product> product = productRepo.findById(productId);
        if(product.isEmpty())
            throw new NotFoundException("Product with id "+productId+" not found while trying to set a product for the cart entry");

        if( checkIfThereAreNotEnoughProductsInStock(quantity, product.get()))
            throw new ProblemWithFieldsException("The quantity is higher than the number in stock");

        Cart cart = optionalCart.get();
        List<CartEntry> cartEntries = cart.getCartEntries();
        cartEntries.forEach(cartEntry -> {
            if( cartEntry.getProduct().getProductId().equals(productId) )
                throw new AlreadyExistsException("An entry with this product already exists");
        });

        CartEntry cartEntry = new CartEntry(
          null,
          quantity,
          cart,
          product.get()
        );
        cart.updateTotalWithNewEntry(cartEntry);
        return this.cartEntryRepo.save(cartEntry);
    }
    @Override
    public CartEntry update(UUID cartId, UUID cartEntryId, CartEntryPatchRequestDTO cartEntryPatchRequestDTO) throws Exception{

        Integer quantity = cartEntryPatchRequestDTO.getQuantity();
        if( quantity <= 0 )
            throw new ProblemWithFieldsException("The quantity should not be below 1");

        Optional<Cart> optionalCart = cartRepo.findById(cartId);
        if(optionalCart.isEmpty())
            throw new NotFoundException("Cart with id "+cartId+" not found while trying to set a product for the cart entry");



        //Checking if the cart has an entry specified by the URI

        List<CartEntry> cartEntries = optionalCart.get().getCartEntries();
        //Initializing it to make java sure it's not null when saving, using the logic it will never save if it's null
        CartEntry cartEntryFound = new CartEntry();
        boolean entryExists = false;

        for(CartEntry cartEntryTemp : cartEntries) {
            if( cartEntryTemp.getCartEntryId().equals(cartEntryId) ){
                if(checkIfThereAreNotEnoughProductsInStock(quantity, cartEntryTemp.getProduct()))
                    throw new ProblemWithFieldsException("The quantity is higher than the number in stock");
                entryExists = true;
                //cloning the object to compare the quantities and calculate the total
                cartEntryFound = new CartEntry(cartEntryTemp);
                cartEntryFound.setQuantity(quantity);
                break;
            }
        }
        if( !entryExists )
            throw new NotFoundException("No cart entry with id "+ cartEntryId+" found in cart "+cartId);

        optionalCart.get().updateTotalWithExistingEntry(cartEntryFound);
        return this.cartEntryRepo.save(cartEntryFound);
    }

    @Override
    public Boolean delete(UUID cartId,UUID cartEntryId) throws Exception{

        Optional<Cart> optionalCart = cartRepo.findById(cartId);
        Cart cart = optionalCart.get();
        List<CartEntry> cartEntries = cart.getCartEntries();

        //Checking if entry exists
        //Initializing it to make java sure it's not null when saving, using the logic it will never save if it's null
        CartEntry cartEntryFound = new CartEntry();
        boolean entryExists = false;
        for(CartEntry cartEntryTemp : cartEntries) {
            if( cartEntryTemp.getCartEntryId().equals(cartEntryId) ){
                entryExists = true;
                cartEntryFound = cartEntryTemp;
                cartEntries.remove(cartEntryTemp);
                break;
            }
        }
        if( !entryExists )
            throw new NotFoundException("Cart Entry with id "+cartEntryId+" not found");

        cart.updateTotalWithDeletedEntry(cartEntryFound);
        cartRepo.save(cart);

        return true;
    }

    public boolean checkIfThereAreNotEnoughProductsInStock(Integer quantity, Product product) {
        return quantity > product.getNumberInStock();
    }

}
