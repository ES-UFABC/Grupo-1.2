package com.es.agriculturafamiliar.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.exception.NotImplementedException;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public Optional<Notificacao> findNotificacaoById(Long id) {
        throw new NotImplementedException();        
    }
    
}