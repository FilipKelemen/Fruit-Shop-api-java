package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.models.Entities.Address;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.AddressType;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.StatusInCart;
import com.FruitShopbackend.FruitShopapi.repo.AddressRepo;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.repo.UserRepo;
import com.FruitShopbackend.FruitShopapi.services.UserService;
import com.FruitShopbackend.FruitShopapi.utility.AuthTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AuthTokenUtility authTokenUtility;
    @Override
    @Transactional
    public Cart createOrObtainCartFromUser(final JwtAuthenticationToken token) {
        final String email = authTokenUtility.getEmailFromAuthToken(token);
        Optional<UserEntity> optionalUser = userRepo.findById(email);
        if(optionalUser.isEmpty()) {
            UserEntity newUser = createNewAccount(email);
            Cart newlyCreatedCart = createNewCart(newUser,token);
            return newlyCreatedCart;
        } else {
            Optional<Cart> optionalActiveCart = findActiveCartOfUser(optionalUser.get());
            if(optionalActiveCart.isEmpty()) {
                return createNewCart(optionalUser.get(),token);
            }
            return optionalActiveCart.get();
        }
    }

    private UserEntity createNewAccount(final String email) {
        UserEntity newUser = new UserEntity(email, false);
        return userRepo.save(newUser);
    }

    private Cart createNewCart(final UserEntity newlyCreatedUser,final JwtAuthenticationToken token) {
        final Cart newCartAssociatedWithUser = new Cart(newlyCreatedUser);
        final Cart savedCart = cartRepo.save(newCartAssociatedWithUser);
        final Address newBillingAddress = new Address(
                AddressType.billing,
                authTokenUtility.getFirstNameFromAuthToken(token),
                authTokenUtility.getLastNameFromAuthToken(token),
                authTokenUtility.getEmailFromAuthToken(token),
                newCartAssociatedWithUser,
                newlyCreatedUser);
        final Address newDeliveryAddress = new Address(
                AddressType.delivery,
                authTokenUtility.getFirstNameFromAuthToken(token),
                authTokenUtility.getLastNameFromAuthToken(token),
                authTokenUtility.getEmailFromAuthToken(token),
                newCartAssociatedWithUser,
                newlyCreatedUser);
        addressRepo.save(newBillingAddress);
        addressRepo.save(newDeliveryAddress);
        return savedCart;
    }

    private Optional<Cart> findActiveCartOfUser(final UserEntity user) {
        final Optional<Cart> activeCart =
                user.getCarts().stream().filter(cart -> cart.getStatus() == StatusInCart.ACTIVE).findFirst();
        return activeCart;
    }
}
