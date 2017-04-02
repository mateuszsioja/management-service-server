package com.msjs.managementservice.exception;

/**
 * Created by jakub on 02.04.2017.
 */
public class ExpiredCredentialsException extends ManagementServiceRuntimeException {

    public ExpiredCredentialsException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
