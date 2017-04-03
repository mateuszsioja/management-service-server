package com.msjs.managementservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * Created by jakub on 02.04.2017.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private ExceptionMessage exceptionMessage;
    private String message;

    public ErrorResponse(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
