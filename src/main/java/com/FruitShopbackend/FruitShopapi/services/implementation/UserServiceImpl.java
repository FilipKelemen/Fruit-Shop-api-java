package com.FruitShopbackend.FruitShopapi.services.implementation;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.AlreadyExistsException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.models.DTOS.LoginUserDTO;
import com.FruitShopbackend.FruitShopapi.models.DTOS.MyUserWithCart;
import com.FruitShopbackend.FruitShopapi.models.DTOS.RegisterUserDTO;
import com.FruitShopbackend.FruitShopapi.models.Entities.Cart;
import com.FruitShopbackend.FruitShopapi.models.Entities.UserEntity;
import com.FruitShopbackend.FruitShopapi.models.Entities.models.StatusInCart;
import com.FruitShopbackend.FruitShopapi.repo.UserRepo;
import com.FruitShopbackend.FruitShopapi.services.UserService;
import com.FruitShopbackend.FruitShopapi.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtility jwtUtility;
//    private AuthenticationManager authenticationManager;
    //Used to get rid of circular dependency injection between AuthenticationManager and UserDetailsService from SecurityConfiguration
//    @Autowired
//    public UserServiceImpl(@Lazy AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    //Registering user
    @Override
    public Map<String, ?> saveUser(RegisterUserDTO user) {
        if( !user.getPassword().equals(user.getConfirmedPassword()) )
            throw new ProblemWithFieldsException("The passwords don't match");

        Optional<UserEntity> userFromDb = userRepo.findByEmail(user.getEmail());
        if(userFromDb.isPresent())
            throw new AlreadyExistsException("The user "+user.getEmail()+" already exists");

        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        List<Cart> listWithOneActiveCart = new ArrayList<Cart>();
        listWithOneActiveCart.add(new Cart());

        UserEntity userThatCanBeSaved = new UserEntity(
                null,
                user.getEmail(),
                encodedPassword,
                user.getFirstName(),
                user.getLastName(),
                null,
                listWithOneActiveCart,
                false
            );
        //TODO not sure how to get rid of this boilerplate and make the cart save the user_id automatically
        UserEntity savedUser = userRepo.save(userThatCanBeSaved);
        //I need these lines cuz the association does not save the foreign key of user in the cart table
        userThatCanBeSaved.getCarts().get(0).setUserEntityInCart(savedUser);
        userRepo.save(userThatCanBeSaved);
        //The jwt
        UserDetails userDetails = new User(
                userThatCanBeSaved.getEmail(),
                userThatCanBeSaved.getPassword(),
                new ArrayList<>() // I don't care about the authorities I just want to generate the jwt
        );
        String jwt = jwtUtility.generateToken(userDetails);
        //The cart
        Cart activeCart = findActiveCart(listWithOneActiveCart);

        return Map.of("jwt", jwt, "cart", activeCart);
    }

    @Override
    public Map<String, ?> authenticateUser(LoginUserDTO userFromRequest) {
        //todo move from controller
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userFromRequest.getEmail(),userFromRequest.getPassword())
//            );
//        } catch (BadCredentialsException exception){
//            throw new ProblemWithFieldsException("Incorrect username and password");
//        }
//
//        //loadUserByUsername returns my custom user details that has a cart attached I did this in order to not be put in a situation
//        //where I need to query the database again
//        final MyUserWithCart userDetails = (MyUserWithCart)loadUserByUsername(userFromRequest.getEmail());
//
//        final String jwt = jwtUtility.generateToken(userDetails);
//
//        final Cart cart = userDetails.getCart();
//        return Map.of("jwt",jwt, "cart", cart);
        return null;
    }


    //Needed by spring security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> userFromDB = userRepo.findByEmail(email);
        //This is inaccessible atm cuz in UserController I am throwing a different exception before this tries to load user
        if(userFromDB.isEmpty())
            throw new UsernameNotFoundException("User with email "+email+" not found");

        String role = userFromDB.get().isAdmin() ? "admin" : "customer";

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return new MyUserWithCart(
                userFromDB.get().getEmail() ,
                userFromDB.get().getPassword(),
                grantedAuthorities,
                findActiveCart(userFromDB.get().getCarts())
        );
    }

    private Cart findActiveCart(List<Cart> listOfAllTheCarts) {
        Cart activeCart = listOfAllTheCarts.stream()
                .filter(cart -> cart.getStatus() == StatusInCart.ACTIVE).findFirst().orElse(null);
        return activeCart;
    }

}
