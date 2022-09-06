package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.StatusInCart;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.repo.UserRepo;
import com.FruitShopbackend.FruitShopapi.services.UserService;
import com.FruitShopbackend.FruitShopapi.utility.AuthTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthTokenUtility authTokenUtility;
    @Override
    @Transactional
    public Cart createOrObtainCartFromUser(JwtAuthenticationToken token) {
        final String email = authTokenUtility.getEmailFromAuthToken(token);
        Optional<UserEntity> optionalUser = userRepo.findById(email);
        if(optionalUser.isEmpty()) {
            Cart newlyCreatedCart = createNewAccountAndCart(email);
            return newlyCreatedCart;
        } else {
            Cart foundCart = findActiveCartOfUser(optionalUser.get());
            return foundCart;
        }
    }

    private Cart createNewAccountAndCart(String email) {
        UserEntity newUser = new UserEntity(email, false);
        UserEntity savedUser = userRepo.save(newUser);
        Cart newCartAssociatedWithUser = new Cart(savedUser);
        return cartRepo.save(newCartAssociatedWithUser);
    }

    private Cart findActiveCartOfUser(UserEntity user) {
        Cart activeCart =
                user.getCarts().stream().filter(cart -> cart.getStatus() == StatusInCart.ACTIVE).findFirst().orElse(null);
        if(activeCart == null) {
            throw new ProblemWithFieldsException("This user has no active carts");
        }
        return activeCart;
    }
}
