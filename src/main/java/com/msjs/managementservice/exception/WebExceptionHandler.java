package com.msjs.managementservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        LOGGER.warn(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(Exception e) {
        LOGGER.warn(e.getMessage(), e);
        return new ErrorResponse(ExceptionMessage.BAD_CREDENTIALS);
    }
}
