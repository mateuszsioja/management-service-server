package com.msjs.managementservice.web.controller;

import com.msjs.managementservice.security.AuthenticationService;
import com.msjs.managementservice.security.TokenUtils;
import com.msjs.managementservice.web.dto.AuthenticationRequest;
import com.msjs.managementservice.web.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.msjs.managementservice.web.controller.ApiUrl.AUTHENTICATION;
import static com.msjs.managementservice.web.controller.ApiUrl.REFRESH;

/**
 * Created by jakub on 01.04.2017.
 */
@RestController
@RequestMapping(AUTHENTICATION)
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TokenUtils tokenUtils;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, TokenUtils tokenUtils) {
        this.authenticationService = authenticationService;
        this.tokenUtils = tokenUtils;
    }

    @PostMapping
    public ResponseEntity<AuthenticationToken> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping(REFRESH)
    public ResponseEntity<AuthenticationToken> refresh(@Valid @RequestBody AuthenticationToken authenticationToken) {
        return ResponseEntity.ok(new AuthenticationToken(tokenUtils.refreshToken(authenticationToken.getToken())));
    }
}
