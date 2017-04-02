package com.msjs.managementservice.security;

import com.msjs.managementservice.web.dto.AuthenticationRequest;
import com.msjs.managementservice.web.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by jakub on 01.04.2017.
 */
@Service
public class AuthenticationService {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    @Autowired
    public AuthenticationService(UserDetailsServiceImpl userDetailsService, AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    public AuthenticationToken authenticate(AuthenticationRequest authenticationRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = tokenUtils.generateToken(userDetails);
        return new AuthenticationToken(token);
    }
}
