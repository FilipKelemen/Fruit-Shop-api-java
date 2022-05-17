package com.FruitShopbackend.FruitShopapi.exception_handling.exceptions;

import com.FruitShopbackend.FruitShopapi.exception_handling.response.MyExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpStatus forbiddenStatus = HttpStatus.FORBIDDEN;
        MyExceptionResponse myExceptionResponse =  new MyExceptionResponse(
                accessDeniedException.getMessage(),
                forbiddenStatus,
                forbiddenStatus.value(),
                LocalDateTime.now()
        );
        response.setContentType("application/json");
        response.setStatus(forbiddenStatus.value()); //todo
        response.getOutputStream().print(myExceptionResponse.serializeToJSON());
    }
}
