package com.es.agriculturafamiliar.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    String generateToken(UserDetails userDetails);    
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
