package com.FruitShopbackend.FruitShopapi.exception_handling.exceptions;

public class ProblemWithFieldsException  extends RuntimeException{

    public ProblemWithFieldsException(String message) {
        super(message);
    }

    public ProblemWithFieldsException(String message, Throwable cause) {
        super(message, cause);
    }
}
