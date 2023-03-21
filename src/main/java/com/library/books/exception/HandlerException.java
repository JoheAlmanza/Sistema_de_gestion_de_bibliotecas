package com.library.books.exception;

import com.library.books.model.response.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(DatoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerDatoNoEncontrado(Exception e){

        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(
                ErrorResponse.builder()
                        .id(UUID.randomUUID())
                        .message(e.getMessage())
                        .date(Date.from(Instant.now()))
                        .build()
        );
    }
    @ExceptionHandler(NoDisponibleException.class)
    public ResponseEntity<ErrorResponse> handlerNoDisponible(Exception d){

        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(
                ErrorResponse.builder()
                        .id(UUID.randomUUID())
                        .message(d.getMessage())
                        .date(Date.from(Instant.now()))
                        .build()
        );
    }
    @ExceptionHandler(MultaPorPrestamoRetrasadoException.class)
    public ResponseEntity<ErrorResponse> handlerMulta(Exception m){

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                ErrorResponse.builder()
                        .id(UUID.randomUUID())
                        .message(m.getMessage())
                        .date(Date.from(Instant.now()))
                        .build()
        );
    }
    @ExceptionHandler(AccionNoRealizadaException.class)
    public ResponseEntity<ErrorResponse> handlerAccionNoRealizada(Exception a){

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                ErrorResponse.builder()
                        .id(UUID.randomUUID())
                        .message(a.getMessage())
                        .date(Date.from(Instant.now()))
                        .build()
        );
    }
}
