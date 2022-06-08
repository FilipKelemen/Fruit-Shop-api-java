package com.FruitShopbackend.FruitShopapi.config;

import com.FruitShopbackend.FruitShopapi.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final String AUTH_HEADER_NAME = "Authorization";
    private final String HEADER_PREFIX = "Bearer ";
    @Autowired
    private JwtUtility jwtUtility;

    //todo add a good way to make a custom response for spring security exceptions

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTH_HEADER_NAME);
        System.out.println("header: "+ authorizationHeader);
        String email = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith(HEADER_PREFIX)){
            jwt = authorizationHeader.substring(HEADER_PREFIX.length());
            email = jwtUtility.extractUsername(jwt);
        }

        System.out.println("Email :" + email);
        System.out.println("jwt: "+ jwt);

        final String requestURI = request.getRequestURI();
        final String cartPath = "/fruits/cart/";

        //The logic of authentication
//        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
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
//        }
        filterChain.doFilter(request, response);
    }

}
