package com.msjs.managementservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.msjs.managementservice.web.controller.ApiUrl.*;

/**
 * Created by jakub on 01.04.2017.
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final List<String> ENDPOINTS_EXCLUDED_FROM_AUTHORIZATION;

    static {
        ENDPOINTS_EXCLUDED_FROM_AUTHORIZATION = new ArrayList<>();
        ENDPOINTS_EXCLUDED_FROM_AUTHORIZATION.add(AUTH_ENDPOINT);
        ENDPOINTS_EXCLUDED_FROM_AUTHORIZATION.add(TOKEN_REFRESH_ENDPOINT);
    }

    private final TokenUtils tokenUtils;

    @Autowired
    public AuthenticationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();
        if (ENDPOINTS_EXCLUDED_FROM_AUTHORIZATION.stream().noneMatch(e -> e.equals(requestURI))) {
            String authToken = httpServletRequest.getHeader(TOKEN_HEADER);
            tokenUtils.validateToken(authToken);
            String username = tokenUtils.getUsernameFromToken(authToken);
            List<GrantedAuthority> authorities = tokenUtils.getAuthoritiesFromToken(authToken);
            UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationContext);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
