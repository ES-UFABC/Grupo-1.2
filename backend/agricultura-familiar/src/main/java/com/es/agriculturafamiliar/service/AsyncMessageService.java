package com.es.agriculturafamiliar.service;

import org.springframework.stereotype.Service;

@Service
public interface AsyncMessageService<T> {
    void sendMessage(T data);    
}
