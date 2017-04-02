package com.msjs.managementservice.web.controller;

import com.msjs.managementservice.security.AuthenticationService;
import com.msjs.managementservice.web.dto.AuthenticationRequest;
import com.msjs.managementservice.web.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationToken> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping(REFRESH)
    public ResponseEntity<AuthenticationToken> refresh(@Valid @RequestBody AuthenticationToken authenticationToken) {
        return ResponseEntity.ok(authenticationService.refreshAuthentication(authenticationToken.getToken()));
    }
}
