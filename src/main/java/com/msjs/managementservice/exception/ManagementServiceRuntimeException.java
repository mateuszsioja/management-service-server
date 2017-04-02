package com.msjs.managementservice.exception;

import lombok.Getter;

/**
 * Created by jakub on 02.04.2017.
 */
@Getter
public class ManagementServiceRuntimeException extends RuntimeException {

    private ErrorMessage errorMessage;

    public ManagementServiceRuntimeException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
