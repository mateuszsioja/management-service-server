package com.msjs.managementservice.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msjs.managementservice.exception.ManagementServiceSecurityException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jakub on 02.04.2017.
 */
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ManagementServiceSecurityException e) {
            prepareResponse(response, e.getExceptionMessage(), HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void prepareResponse(HttpServletResponse response, Object message, int status) throws IOException {
        response.setStatus(status);
        response.getWriter().write(convertObjectToJson(message));
    }

    private String convertObjectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().withRootName("errorMessage").writeValueAsString(obj);
    }
}
