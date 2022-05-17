package com.FruitShopbackend.FruitShopapi.config;

import com.FruitShopbackend.FruitShopapi.models.DTOS.MyUserWithCart;
import com.FruitShopbackend.FruitShopapi.services.implementation.UserServiceImpl;
import com.FruitShopbackend.FruitShopapi.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtility jwtUtility;

    //todo add a good way to make a custom response for spring security exceptions
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtility.extractUsername(jwt);
        }

        final String requestURI = request.getRequestURI();
        final String cartPath = "/fruits/cart/";

        //The logic of authentication
        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //checking if the cart id in uri matches the one found in the user

            if(requestURI.startsWith(cartPath)){
                String cartIdFromURI =
                        requestURI.substring(cartPath.length(),cartPath.length() + UUID.randomUUID().toString().length());
                MyUserWithCart userDetails = (MyUserWithCart)userService.loadUserByUsername(username);
                if(jwtUtility.validateToken(jwt, userDetails) && userDetails.getCart().getCartId().toString().equals(cartIdFromURI)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
