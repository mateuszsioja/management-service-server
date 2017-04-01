package com.msjs.managementservice.web.controller;

import com.msjs.managementservice.web.dto.AuthenticationRequestDto;
import com.msjs.managementservice.web.dto.AuthenticationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.msjs.managementservice.web.controller.ApiUrl.AUTHENTICATION;

/**
 * Created by jakub on 01.04.2017.
 */
@RestController
@RequestMapping(AUTHENTICATION)
public class AuthenticationController {

    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> authenticate(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return null;
    }
}
