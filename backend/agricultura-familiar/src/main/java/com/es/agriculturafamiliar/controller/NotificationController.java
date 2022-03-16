package com.es.agriculturafamiliar.controller;

import java.util.Optional;

import com.es.agriculturafamiliar.entity.Notificacao;

import com.es.agriculturafamiliar.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<?> findNotificacaoById(@RequestParam Long id)  {
        Optional<Notificacao> findNotificacaoById = notificationService.findNotificacaoById(id); 
        
        if (findNotificacaoById.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(findNotificacaoById);
    }

    
}
