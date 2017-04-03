package com.msjs.managementservice.security;

import com.msjs.managementservice.exception.ExceptionMessage;
import com.msjs.managementservice.exception.ManagementServiceSecurityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jakub on 01.04.2017.
 */
public abstract class TokenUtils {

    private static final String ROLES = "roles";
    private static final String CREATED_DATE = "created";
    private static final String EXPIRATION_DATE = "expiration";
    private static final String SECRET = "secret";
    private static final String USERNAME = "username";
    private static final int HALF_HOUR = 30;

    static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, userDetails.getUsername());
        claims.put(ROLES, userDetails.getAuthorities());
        claims.put(CREATED_DATE, new Date());
        claims.put(EXPIRATION_DATE, generateExpirationDate());

        return generateToken(claims);
    }

    static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    static String getUsernameFromToken(String token) {
        return (String) getClaimsFromToken(token).get(USERNAME);
    }

    static List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Object authorities = getClaimsFromToken(token).get(ROLES);
        Object map = ((ArrayList) authorities).get(0);
        return ((LinkedHashMap<String, String>) map).entrySet().stream()
                .map(x -> new SimpleGrantedAuthority(x.getValue()))
                .collect(Collectors.toList());
    }

    static void validateToken(String token) {
        Claims claims;
        try {
            claims = getClaimsFromToken(token);
        } catch (MalformedJwtException e) {
            throw new ManagementServiceSecurityException(ExceptionMessage.BAD_TOKEN);
        }
        Long milliSeconds = (Long) claims.get(EXPIRATION_DATE);
        Date expiryDate = new Date(milliSeconds);
        if (new Date().after(expiryDate)) {
            throw new ManagementServiceSecurityException(ExceptionMessage.TOKEN_EXPIRED);
        }
    }

    static Claims getClaimsFromToken(String token) {
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    static Claims addNewExpirationDateToClaims(Claims claims) {
        claims.put(EXPIRATION_DATE, generateExpirationDate());
        return claims;
    }

    private static Date generateExpirationDate() {
        return DateUtils.addMinutes(new Date(), HALF_HOUR);
    }
}
