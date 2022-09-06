package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.StatusInCart;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.repo.UserRepo;
import com.FruitShopbackend.FruitShopapi.services.CartRouteHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartRouteHandlingServiceImpl implements CartRouteHandlingService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Value("${URL_PATHS.cart}")
    private String CART_PATH;
    CartRouteHandlingServiceImpl(){}

    @Override
    @Transactional
    public void handleCartValidity(final String requestURI, final String email) throws ServletException {
        Optional<UserEntity> optionalUser = userRepo.findById(email);
        if(optionalUser.isEmpty()) {
            throw new ServletException("No user found with this email " + email);
        }
        UserEntity authenticatedUser = optionalUser.get();
        String cartIdFromURI =
                requestURI.substring(CART_PATH.length(),CART_PATH.length() + UUID.randomUUID().toString().length());
        Cart foundCartAssociatedWithUserMatchingTheCartIdFromURI =
                authenticatedUser.getCarts().stream().filter(cart -> cart.getCartId().toString().equals(cartIdFromURI)).findFirst().orElse(null);
        //if the "if" passes, the cart in uri is NOT the same as the one in database associated with the user
        if( foundCartAssociatedWithUserMatchingTheCartIdFromURI == null) {
            //todo add custom error handling
            throw new ServletException("The cart does not belong to this user");
        }
        if( foundCartAssociatedWithUserMatchingTheCartIdFromURI.getStatus() != StatusInCart.ACTIVE) {
            //todo add custom error handling
            throw new ServletException("The order associated with this cart was already done");
        }
    }

}
