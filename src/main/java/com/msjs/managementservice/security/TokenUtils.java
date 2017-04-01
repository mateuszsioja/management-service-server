package com.msjs.managementservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 01.04.2017.
 */
public class TokenUtils {

    private static final String ROLES = "roles";
    private static final String CREATED_DATE = "created";
    private static final String SECRET = "secret";
    private static final Long EXPIRATION = 300000L;

    public static String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put(ROLES, userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public static List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Object authorities = getClaimsFromToken(token).get(ROLES);
        Object map = ((ArrayList) authorities).get(0);
        return ((LinkedHashMap<String, String>) map).entrySet().stream()
                .map(x -> new SimpleGrantedAuthority(x.getValue()))
                .collect(Collectors.toList());
    }

    public static void validateToken(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        checkIfTokenPassedTimeLimit(expirationDate);
    }

    private static Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private static void checkIfTokenPassedTimeLimit(Date expirationDate) {
        if(expirationDate.after(new Date())) {
            throw new RuntimeException("token expired");
        }
    }

    private static Claims getClaimsFromToken(String token) {
        //fix this to return 2 date objects
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJwt(token)
                .getBody();
        return claims;
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    }
}
