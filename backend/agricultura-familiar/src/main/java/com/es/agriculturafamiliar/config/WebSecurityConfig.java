package com.es.agriculturafamiliar.config;

import com.es.agriculturafamiliar.constants.RoleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "security.basic.enabled",
    havingValue = "true"
    
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private FIlterChainExceptionHandler filterChainExceptionHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {        
        httpSecurity
                .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
                .cors().disable()
                .csrf().disable()            
                .authorizeHttpRequests()
                
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .regexMatchers(".*/admin.*").hasRole(RoleType.ADMIN.name())
                .antMatchers("/consumidor/*").hasRole(RoleType.CONSUMIDOR.name())
                .antMatchers("/produtor/*").hasRole(RoleType.PRODUTOR.name())            
                .antMatchers("/cadastro/*", "/login/*").permitAll()
                .anyRequest().permitAll()                
            .and()            
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
}
