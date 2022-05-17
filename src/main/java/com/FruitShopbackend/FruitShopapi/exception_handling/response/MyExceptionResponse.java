package com.FruitShopbackend.FruitShopapi.exception_handling.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class MyExceptionResponse {
    private final String message;
    private final HttpStatus status;
    private final int statusCode;
    private final LocalDateTime timeStamp;

    public MyExceptionResponse(String message,
                               HttpStatus status,
                               int statusCode,
                               LocalDateTime timeStamp) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode(){return statusCode;}

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    //used only for Authentication Exceptions
    public String serializeToJSON() {
        return "{ \"message\": \"" + message + "\" " +
               ", \"status\": \"" + status.getReasonPhrase() + "\" " +
               ", \"statusCode\": \"" + statusCode + "\" " +
               ", \"timeStamp\": \"" + timeStamp + "\" }";
    }
}
