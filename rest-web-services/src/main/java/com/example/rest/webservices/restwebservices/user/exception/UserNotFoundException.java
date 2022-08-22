package com.example.rest.webservices.restwebservices.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    //NOTE : This is constructor
    public UserNotFoundException(String massage) {
        super(massage);

    }
}
