package com.spring.webflux.springwebflux.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorParam {

    private String field;
    private List<ItemError> messages;

    public ErrorParam(String field, List<ItemError> messages) {
        this.field = field;
        this.messages = messages;
    }
}
