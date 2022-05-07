package com.es.agriculturafamiliar.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.es.agriculturafamiliar.enums.RoleType;

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
                .antMatchers(HttpMethod.GET, "/notificacao").hasAnyRole(RoleType.PRODUTOR.name(), RoleType.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/notificacao/{\\\\d+}").hasAnyRole(RoleType.PRODUTOR.name(), RoleType.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/produtor/{\\\\d+}").hasAnyRole(RoleType.PRODUTOR.name(), RoleType.ADMIN.name(), RoleType.CONSUMIDOR.name())
                .antMatchers(HttpMethod.GET, "/produtor/*").hasAnyRole(RoleType.ADMIN.name(), RoleType.PRODUTOR.name(), RoleType.CONSUMIDOR.name())
                .antMatchers(HttpMethod.POST, "/produtor/*").hasAnyRole(RoleType.PRODUTOR.name(), RoleType.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/produtor/*").hasAnyRole(RoleType.ADMIN.name(), RoleType.PRODUTOR.name())
                .antMatchers(HttpMethod.DELETE, "/produtor/*").hasAnyRole(RoleType.ADMIN.name())
                .antMatchers("/cadastro/*", "/login/*").permitAll()
                .anyRequest().permitAll()                
            .and()            
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
}
