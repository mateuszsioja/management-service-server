package com.msjs.managementservice.web.exception;

import com.msjs.managementservice.exception.ErrorMessage;
import com.msjs.managementservice.exception.ExpiredCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by jakub on 02.04.2017.
 */
@ControllerAdvice
@RestController
@EnableWebMvc
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

    //TODO CUSTOM EXCEPTIONS ARE NOT WORKING....
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleExpiredTokenException(Exception e) {
        LOGGER.warn(e.getMessage(), e);
        //return new ResponseEntity(new ErrorResponse(e.getErrorMessage()), HttpStatus.UNAUTHORIZED);
        return null;
    }

    @ExceptionHandler(ExpiredCredentialsException.class)
    public ErrorResponse handleExpiredTokenException(ExpiredCredentialsException e) {
        LOGGER.warn(e.getMessage(), e);
        return new ErrorResponse(e.getErrorMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleExpiredTokenException(BadCredentialsException e) {
        LOGGER.warn(e.getMessage(), e);
        return new ErrorResponse(ErrorMessage.BAD_CREDENTIALS);
    }
}
