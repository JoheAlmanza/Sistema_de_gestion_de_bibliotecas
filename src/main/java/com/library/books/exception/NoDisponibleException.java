package com.library.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDisponibleException extends RuntimeException{
    public NoDisponibleException(String message){
        super(message);
    }
}
