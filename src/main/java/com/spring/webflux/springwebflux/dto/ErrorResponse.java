package com.spring.webflux.springwebflux.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private static ErrorResponse errorResponse;

    private String message;

    private List<?> details;

    public ErrorResponse() {
        super();
    }

    public static ErrorResponse getInstancia(String message, List<?> details) {
        if (errorResponse == null) {
            errorResponse = new ErrorResponse();
        }

        errorResponse.setMessage(message);
        errorResponse.setDetails(details);

        return errorResponse;
    }
}
