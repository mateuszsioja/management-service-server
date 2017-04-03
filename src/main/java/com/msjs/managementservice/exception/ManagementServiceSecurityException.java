package com.msjs.managementservice.exception;

import lombok.Getter;

/**
 * Created by jakub on 02.04.2017.
 */
@Getter
public class ManagementServiceSecurityException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public ManagementServiceSecurityException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
