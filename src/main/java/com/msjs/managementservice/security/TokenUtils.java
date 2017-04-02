package com.msjs.managementservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jakub on 01.04.2017.
 */
@Component
public class TokenUtils {

    private static final String ROLES = "roles";
    private static final String CREATED_DATE = "created";
    private static final String EXPIRATION_DATE = "expiration";
    private static final String SECRET = "secret";
    private static final int HALF_HOUR = 30;
    private static final String USERNAME = "username";

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public TokenUtils(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, userDetails.getUsername());
        claims.put(ROLES, userDetails.getAuthorities());
        claims.put(CREATED_DATE, new Date());
        claims.put(EXPIRATION_DATE, generateExpirationDate());

        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                //.setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Object authorities = getClaimsFromToken(token).get(ROLES);
        Object map = ((ArrayList) authorities).get(0);
        return ((LinkedHashMap<String, String>) map).entrySet().stream()
                .map(x -> new SimpleGrantedAuthority(x.getValue()))
                .collect(Collectors.toList());
    }

    public String refreshToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        String username = (String) claimsFromToken.get(USERNAME);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails.isCredentialsNonExpired()) {
            claimsFromToken.put(EXPIRATION_DATE, generateExpirationDate());
            return generateToken(claimsFromToken);
        } else {
            throw new RuntimeException("cred expired");
        }
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public void validateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Long milliSeconds = (Long) claims.get(EXPIRATION_DATE);
        Date expiryDate = new Date(milliSeconds);
        if (new Date().after(expiryDate)) {
            throw new RuntimeException("token expired");
        }
    }

    private static Date generateExpirationDate() {
        return DateUtils.addMinutes(new Date(), 1);
    }
}
