package com.msjs.managementservice.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jakub on 01.04.2017.
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String AUTH_ENDPOINT = "/api/auth";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();

        if(!requestURI.equals(AUTH_ENDPOINT)) {
            String authToken = httpServletRequest.getHeader(TOKEN_HEADER);
            TokenUtils.validateToken(TOKEN_HEADER);
            String username = TokenUtils.getUsernameFromToken(authToken);
            List<GrantedAuthority> authorities = TokenUtils.getAuthoritiesFromToken(authToken);
            UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationContext);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
