package com.msjs.managementservice.exception;

/**
 * Created by jakub on 02.04.2017.
 */
public enum ExceptionMessage {
    TOKEN_EXPIRED("Token has expired. Refresh your token if you want to access this resource"),
    CREDENTIALS_EXPIRED("Credentials are expired. You cannot refresh this token"),
    BAD_CREDENTIALS("Provided credentials are not valid"),
    BAD_TOKEN("Provided token is not valid"),
    NO_AUTHORIZATION_PROVIDED("No authorization provided");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
