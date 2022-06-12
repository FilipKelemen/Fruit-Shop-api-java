package com.FruitShopbackend.FruitShopapi.utility;

import com.FruitShopbackend.FruitShopapi.utility.CustomJWT.CustomClaims;
import com.FruitShopbackend.FruitShopapi.utility.CustomJWT.CustomDefaultClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
//todo this is obsolete at the moment together with the CustomClaims and DefaultCustomClaims, just proud I made them work
//oauth2 already has a built in easy way to extract eny claim
@Service
public class JwtUtility implements Serializable {

    public String extractEmail(String token) {
        return extractClaim(token, CustomClaims::getEmail);
    }
    public String extractUsername(String token) {
        return extractClaim(token, CustomClaims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, CustomClaims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<CustomClaims, T> claimsResolver) {
        final CustomClaims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private CustomClaims extractAllClaims(String token) {
        String tokenWithoutSignature = getTokenWithoutSignature(token);
        //converting claims to my custom claims in order to access email
        Claims myClaims = Jwts.parser().parseClaimsJwt(tokenWithoutSignature).getBody();
        CustomClaims customClaims = new CustomDefaultClaims(myClaims);
        return customClaims;
    }

    //I remove the signature to decode it, as Google already authorized the token
    private String getTokenWithoutSignature(String token) {
        int indexOfSignature = token.lastIndexOf('.');
        return token.substring(0, indexOfSignature + 1);
    }

}
