package com.library.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DatoNoEncontradoException extends RuntimeException{
    public DatoNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
