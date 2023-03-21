package com.library.books.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ErrorResponse {
    private UUID id;
    private String message;
    private Date date;
}
