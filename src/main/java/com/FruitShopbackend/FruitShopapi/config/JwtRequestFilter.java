package com.FruitShopbackend.FruitShopapi.config;

import com.FruitShopbackend.FruitShopapi.services.CartRouteHandlingService;
import com.FruitShopbackend.FruitShopapi.utility.AuthTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//App gets here only if authentication was successful
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${URL_PATHS.cart}")
    private String CART_PATH;
    //todo add a good way to make a custom response for spring security exceptions

    @Autowired
    private CartRouteHandlingService cartRouteHandlingService;
    @Autowired
    private AuthTokenUtility authTokenUtility;

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
        final String requestURI = request.getRequestURI();

        if(requestURI.startsWith(CART_PATH)) {
            String email = authTokenUtility.getEmailFromAuthToken(token);
            cartRouteHandlingService.handleCartValidity(requestURI, email);
        }

        filterChain.doFilter(request, response);
    }
}
