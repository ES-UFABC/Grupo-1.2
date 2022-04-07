package com.es.agriculturafamiliar.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.service.ICustomUserDetailsService;
import com.es.agriculturafamiliar.service.ITokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final ICustomUserDetailsService<User> jwtUserDetailsManager;
    private final ITokenService tokenService;
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String JWT_HEADER_PREFIX = "Bearer";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, UsernameNotFoundException, AuthException{

            String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
            if (authorizationHeader != null && authorizationHeader.startsWith(JWT_HEADER_PREFIX)) {
                String token = authorizationHeader.split(" ")[1];
                String username = tokenService.getUsernameFromToken(token);
                UserDetails userDetails = jwtUserDetailsManager.loadUserByUsername(username);
                
                if (tokenService.isTokenValid(token, userDetails)) {
                    setAuthenticatedUserOnSecurityContext(request, userDetails);
                }
            }
    
            filterChain.doFilter(request, response);
    }

    private void setAuthenticatedUserOnSecurityContext(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
        WebAuthenticationDetails webAuthDetails = webAuthenticationDetailsSource.buildDetails(request);

        usernamePasswordAuthenticationToken.setDetails(webAuthDetails);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}
