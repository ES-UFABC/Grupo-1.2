package com.es.agriculturafamiliar.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICustomUserDetailsService<T> extends UserDetailsService {

    public T createUser(UserDetails user);

    
}
