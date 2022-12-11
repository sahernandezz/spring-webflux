package com.spring.webflux.springwebflux.rest;

import com.spring.webflux.springwebflux.dto.ErrorParam;
import com.spring.webflux.springwebflux.dto.ErrorResponse;
import com.spring.webflux.springwebflux.exception.RecordNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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

@SuppressWarnings({"unchecked", "rawtypes"})
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<Object> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = ErrorResponse.getInstancia("Server Error", details);
        return ResponseEntity.internalServerError()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(error);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        List<Object> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = ErrorResponse.getInstancia("Record Not Found", details);
        return ResponseEntity.internalServerError()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse error = ErrorResponse.getInstancia("Validation Failed", details(ex.getBindingResult().getFieldErrors()));
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(error);
    }

    private List<ErrorParam> details(final List<FieldError> list) {
        List<ErrorParam> details = new ArrayList<>();
        for (FieldError error : list) {
            if (details.stream().filter(e -> e.getField().equals(error.getField())).toList().isEmpty()) {
                ErrorParam param = new ErrorParam(error.getField(),
                        list.stream().filter(e ->
                                        e.getField().equals(error.getField())).toList().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
                details.add(param);
            }
        }
        return details;
    }
}
