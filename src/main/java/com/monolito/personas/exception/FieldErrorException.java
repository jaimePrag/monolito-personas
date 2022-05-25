package com.monolito.personas.exception;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class FieldErrorException extends RuntimeException {

    public FieldErrorException(BindingResult result) {
        super(getStringErrors(result));
    }

    private static String getStringErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));
    }

}
