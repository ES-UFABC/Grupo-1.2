package com.es.agriculturafamiliar.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    private String token;
    private String expirationDate;
    private AuthenticatedUser user;
}
