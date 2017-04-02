package com.msjs.managementservice.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jakub on 01.04.2017.
 */
public class PasswordEncoderGenerator {

    public static String generate(String password) {
        String hashedPass = "";
        for (int i = 0; i < 10; i++) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPass = passwordEncoder.encode(password);
        }
        return hashedPass;
    }
}