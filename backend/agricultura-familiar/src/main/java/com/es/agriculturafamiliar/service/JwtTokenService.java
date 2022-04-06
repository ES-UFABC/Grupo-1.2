package com.es.agriculturafamiliar.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService implements ITokenService {

    /**
     * Pending, extract to app-props
     */
    private static final String SECRET_KEY = "abacate";
    private static final int TOKEN_VALIDITY = 60 * 60 * 3;

    @Override
    public String generateToken(UserDetails userDetails) {      
        Date issuedAt = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        Date expirationTime = Date.from(LocalDateTime.now().plusHours(TOKEN_VALIDITY).toInstant(ZoneOffset.UTC));

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(issuedAt)
            .setExpiration(expirationTime)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)          
            .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {        
        return getClaimsFromToken(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        boolean isValidUsername = username.equals(userDetails.getUsername());
        return isValidUsername && isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = getClaimsFromToken(token, Claims::getExpiration);
        final Date now = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        return expirationDate.before(now);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimCallback) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimCallback.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
}
