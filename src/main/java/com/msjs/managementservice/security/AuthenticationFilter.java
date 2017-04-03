package com.msjs.managementservice.security;

import org.apache.commons.lang3.StringUtils;
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

import static com.msjs.managementservice.security.TokenUtils.*;
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

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authToken = httpServletRequest.getHeader(TOKEN_HEADER);
        if (endpointRequiresAuthenticationAndTokenIsPresent(httpServletRequest, authToken)) {
            validateToken(authToken);
            String username = getUsernameFromToken(authToken);
            List<GrantedAuthority> authorities = getAuthoritiesFromToken(authToken);
            UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationContext);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean endpointRequiresAuthenticationAndTokenIsPresent(HttpServletRequest request, String authToken) {
        String requestURI = request.getRequestURI();
        return ENDPOINTS_EXCLUDED_FROM_AUTHORIZATION.stream().noneMatch(e -> e.equals(requestURI)) && StringUtils.isNotBlank(authToken);
    }
}
