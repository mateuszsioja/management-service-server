package com.msjs.managementservice.security;

import com.msjs.managementservice.exception.ErrorMessage;
import com.msjs.managementservice.exception.ExpiredCredentialsException;
import com.msjs.managementservice.web.dto.AuthenticationRequest;
import com.msjs.managementservice.web.dto.AuthenticationToken;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.msjs.managementservice.security.TokenUtils.*;

/**
 * Created by jakub on 01.04.2017.
 */
@Service
public class AuthenticationService {

    private static final String EXPIRED_CREDENTIALS = "Token cannot be refreshed, because credentials has been changed.";
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserDetailsServiceImpl userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
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

        final String token = generateToken(userDetails);
        return new AuthenticationToken(token);
    }

    public AuthenticationToken refreshAuthentication(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        String username = getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails.isCredentialsNonExpired()) {
            claimsFromToken = addNewExpirationDateToClaims(claimsFromToken);
            return new AuthenticationToken(generateToken(claimsFromToken));
        } else {
            throw new ExpiredCredentialsException(ErrorMessage.CREDENTIALS_EXPIRED);
        }
    }
}
