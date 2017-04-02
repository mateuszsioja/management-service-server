package com.msjs.managementservice.exception;

/**
 * Created by jakub on 02.04.2017.
 */
public class ExpiredTokenException extends ManagementServiceRuntimeException {

    public ExpiredTokenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
