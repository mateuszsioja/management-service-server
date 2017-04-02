package com.msjs.managementservice.exception;

/**
 * Created by jakub on 02.04.2017.
 */
public enum ErrorMessage {
    TOKEN_EXPIRED("Token has expired. Refresh your token if you want to access this resource"),
    CREDENTIALS_EXPIRED("Credentials are expired. You cannot refresh this token"),
    BAD_CREDENTIALS("Provided credentials are not valid");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
