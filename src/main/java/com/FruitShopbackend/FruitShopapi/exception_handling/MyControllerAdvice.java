package com.FruitShopbackend.FruitShopapi.exception_handling;

import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.AlreadyExistsException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.NotFoundException;
import com.FruitShopbackend.FruitShopapi.exception_handling.exceptions.ProblemWithFieldsException;
import com.FruitShopbackend.FruitShopapi.exception_handling.response.MyExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(
            NotFoundException exception
    ) {
        MyExceptionResponse notFoundExceptionResponse = new MyExceptionResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(notFoundExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNotAllFieldsCompletedException(
            ProblemWithFieldsException exception
    ) {
        MyExceptionResponse notAllFieldsCompletedExceptionResponse = new MyExceptionResponse(
                    exception.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value(),
                    LocalDateTime.now()
        );
        return new ResponseEntity<>(
                notAllFieldsCompletedExceptionResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAlreadyExistsException(
            AlreadyExistsException exception
    ) {
        MyExceptionResponse alreadyExistsExceptionResponse =  new MyExceptionResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(
          alreadyExistsExceptionResponse,
                HttpStatus.CONFLICT
        );
    }

}
