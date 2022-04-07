package com.es.agriculturafamiliar.service;

import com.es.agriculturafamiliar.entity.JwtToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    JwtToken generateToken(UserDetails userDetails);    
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
