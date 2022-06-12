package com.FruitShopbackend.FruitShopapi.config;

import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import com.FruitShopbackend.FruitShopapi.repo.CartRepo;
import com.FruitShopbackend.FruitShopapi.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//App gets here only if authentication was successful
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final String EMAIL_CLAIM_NAME = "email";

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;

    //todo add a good way to make a custom response for spring security exceptions

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        final Authentication token = SecurityContextHolder.getContext().getAuthentication();
        //from this point, the user is authenticated but tries to access a public resource we resume the flow, as we don't need
        //any additional instructions
        if(!(token instanceof JwtAuthenticationToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        //as of here, the user is authenticated and tries to access a protected resource
        final JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) token;
        final Jwt credentials = (Jwt)authenticationToken.getCredentials();
        final String email = credentials.getClaim(EMAIL_CLAIM_NAME);
        final String requestURI = request.getRequestURI();
        handleNewOrOldUser(requestURI,email);

//            //checking if the cart id in uri matches the one found in the user
//
//            if(requestURI.startsWith(cartPath)){
//                String cartIdFromURI =
//                        requestURI.substring(cartPath.length(),cartPath.length() + UUID.randomUUID().toString().length());
//                MyUserWithCart userDetails = (MyUserWithCart)userService.loadUserByUsername(username);
//                if(jwtUtility.validateToken(jwt, userDetails) && userDetails.getCart().getCartId().toString().equals(cartIdFromURI)) {
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
        filterChain.doFilter(request, response);
    }
    @Transactional
    void handleNewOrOldUser(final String requestURI,final String email) {
        Optional<UserEntity> optionalUser = userRepo.findById(email);
        List<Cart> cartList = optionalUser.get().getCarts();
//
//        if(optionalUser.isEmpty()) {
//            createNewAccount(email);
//        } else {
//            UserEntity user = optionalUser.get();
//            checkCartAndAddressValidityIfNeeded(user, email, requestURI);
//        }
    }

    private void createNewAccount(String email) {
        UserEntity newUser = new UserEntity(email, false);
        UserEntity savedUser = userRepo.save(newUser);
        Cart newCartAssociatedWithUser = new Cart(savedUser);
        cartRepo.save(newCartAssociatedWithUser);
    }

    private void checkCartAndAddressValidityIfNeeded(final UserEntity authenticatedUser,final String email,final String requestURI) {
        final String CART_PATH = "/fruits/cart/";
        final String ADDRESS_PATH = "/fruits/cart/**/address/";
        if(requestURI.startsWith(CART_PATH)) {
            handleCartValidity(authenticatedUser, email,requestURI, CART_PATH);
        }
    }

    private void handleCartValidity(final UserEntity authenticatedUser,final String email, String requestURI,final String CART_PATH) {
        String cartIdFromURI =
                requestURI.substring(CART_PATH.length(),CART_PATH.length() + UUID.randomUUID().toString().length());
        Cart foundCartAssociatedWithUserMatchingTheCartIdFromURI =
                authenticatedUser.getCarts().stream().filter(cart -> cart.getCartId().toString().equals(cartIdFromURI)).findFirst().orElse(null);
        //if the "if" passes, the cart in uri is the same as the one in database associated with the user
        if( foundCartAssociatedWithUserMatchingTheCartIdFromURI != null) {
            System.out.println("The cart is good");
        } else {
            System.out.println("The cart is bad");
        }
    }

}
