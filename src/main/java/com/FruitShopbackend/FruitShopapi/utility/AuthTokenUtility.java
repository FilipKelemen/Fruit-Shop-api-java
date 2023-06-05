package com.FruitShopbackend.FruitShopapi.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenUtility {
    private final String EMAIL_CLAIM_NAME = "email";
    private final String FIRST_NAME_CLAIM_NAME = "given_name";
    private final String LAST_NAME_CLAIM_NAME = "family_name";

    public String getEmailFromAuthToken(Authentication token) {
        Jwt credentials = getCredentialsFromAuthToken(token);
        return credentials.getClaim(EMAIL_CLAIM_NAME);
    }

    public String getFirstNameFromAuthToken(Authentication token) {
        Jwt credentials = getCredentialsFromAuthToken(token);
        return credentials.getClaim(FIRST_NAME_CLAIM_NAME);
    }

    public String getLastNameFromAuthToken(Authentication token) {
        Jwt credentials = getCredentialsFromAuthToken(token);
        return credentials.getClaim(LAST_NAME_CLAIM_NAME);
    }

    public Jwt getCredentialsFromAuthToken(Authentication token) {
        final JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) token;
        return (Jwt)authenticationToken.getCredentials();
    }

}
