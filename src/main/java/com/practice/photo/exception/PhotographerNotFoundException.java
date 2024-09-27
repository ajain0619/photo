package com.practice.photo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such photographer!")
public class PhotographerNotFoundException extends RuntimeException{

    public PhotographerNotFoundException(Long id) {
        super("photographer not found with id: " + id);
    }
}