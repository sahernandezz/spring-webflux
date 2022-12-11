package com.spring.webflux.springwebflux.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorParam {

    private String field;
    private List<String> messages;

    public ErrorParam(String field, List<String> messages) {
        this.field = field;
        this.messages = messages;
    }
}
