package com.FruitShopbackend.FruitShopapi.services;

import javax.servlet.ServletException;

public interface CartRouteHandlingService {

    void handleCartValidity(final String requestURI,final String email) throws ServletException;
}
