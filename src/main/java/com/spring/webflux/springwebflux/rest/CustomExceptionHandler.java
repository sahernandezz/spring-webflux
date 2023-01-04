package com.spring.webflux.springwebflux.rest;

import com.spring.webflux.springwebflux.dto.ErrorParam;
import com.spring.webflux.springwebflux.dto.ErrorResponse;
import com.spring.webflux.springwebflux.dto.ItemError;
import com.spring.webflux.springwebflux.exception.RecordNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
     *
     * @param ex the MethodArgumentNotValidException that is thrown when @Valid
     * @return the ErrorParam object
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(final Exception ex) {
        List<Object> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = ErrorResponse.getInstancia("Server Error", details);
        return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON_UTF8).body(error);
    }

    /**
     * Maneja la excepción RecordNotFoundException
     *
     * @param ex Excepción
     * @return ResponseEntity
     */
    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(final RecordNotFoundException ex) {
        List<Object> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = ErrorResponse.getInstancia("Record Not Found", details);
        return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON_UTF8).body(error);
    }

    /**
     * This exception is thrown when method argument is not the expected type.
     *
     * @param ex      the exception to handle
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        ErrorResponse error = ErrorResponse.getInstancia("Validation Failed", details(ex.getBindingResult().getFieldErrors()));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(error);
    }

    /**
     * Custom error response
     *
     * @param list of field errors
     * @return list of error params
     */
    private List<ErrorParam> details(final List<FieldError> list) {
        List<ErrorParam> details = new ArrayList<>();
        for (FieldError error : list) {
            if (details.stream().filter(e -> e.getField().equals(error.getField())).toList().isEmpty()) {// is not in the list
                details.add(new ErrorParam(error.getField(), list.stream().filter(e -> e.getField()
                        .equals(error.getField())).toList().stream().map(a -> new ItemError(a.getCode(), a.getDefaultMessage())).toList()));
            }
        }
        return details;
    }
}
