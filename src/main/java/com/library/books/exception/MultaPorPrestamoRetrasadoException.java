package com.library.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class MultaPorPrestamoRetrasadoException extends RuntimeException{
    public MultaPorPrestamoRetrasadoException(String message){
        super(message);
    }
}

