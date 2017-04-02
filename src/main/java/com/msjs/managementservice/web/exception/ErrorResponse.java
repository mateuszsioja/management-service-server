package com.msjs.managementservice.web.exception;

import com.msjs.managementservice.exception.ErrorMessage;
import lombok.Getter;

/**
 * Created by jakub on 02.04.2017.
 */
@Getter
public class ErrorResponse {
    private final ErrorMessage errorMessage;

    public ErrorResponse(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
