package com.es.agriculturafamiliar.controller;

import java.net.URI;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.es.agriculturafamiliar.dto.NotificacaoDTO;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.mapper.NotificacaoMapper;
import com.es.agriculturafamiliar.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/v1/admin/notifications")
@AllArgsConstructor
public class NotificationController {
    
    @Autowired
    private final NotificacaoMapper notificacaoMapper;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findNotificacaoById(@PathVariable Long id)  {
        Optional<Notificacao> findNotificacaoById = notificationService.findNotificacaoById(id); 
        
        if (findNotificacaoById.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(findNotificacaoById);
    }

    @PostMapping
    public ResponseEntity<?> saveNotificacao(@RequestBody NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = notificacaoMapper.NotificacaoDTOToNotificacao(notificacaoDTO);        
        notificationService.saveNotificacao(notificacao);
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .build();
    }

    
}
