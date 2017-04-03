package com.msjs.managementservice.security;

import com.msjs.managementservice.exception.ExceptionMessage;
import com.msjs.managementservice.exception.ManagementServiceSecurityException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by jakub on 01.04.2017.
 */
public class TokenAuthenticationEntryPoint implements Serializable, AuthenticationEntryPoint {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        throw new ManagementServiceSecurityException(ExceptionMessage.NO_AUTHORIZATION_PROVIDED);
    }
}
