package com.FruitShopbackend.FruitShopapi.exception_handling.exceptions;

import com.FruitShopbackend.FruitShopapi.exception_handling.response.MyExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        HttpStatus unauthorizedStatus = HttpStatus.UNAUTHORIZED;
        MyExceptionResponse myExceptionResponse =  new MyExceptionResponse(
                authException.getMessage(),
                unauthorizedStatus,
                unauthorizedStatus.value(),
                LocalDateTime.now()
        );
        response.setContentType("application/json");
        response.setStatus(unauthorizedStatus.value());
        response.getOutputStream().print(myExceptionResponse.serializeToJSON());
    }
}
