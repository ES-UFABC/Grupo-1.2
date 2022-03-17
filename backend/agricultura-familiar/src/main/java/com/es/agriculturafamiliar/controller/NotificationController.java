package com.es.agriculturafamiliar.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.es.agriculturafamiliar.dto.NotificacaoDTO;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.service.NotificationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin/notifications")
public class NotificationController {
    
    @Autowired
    private ModelMapper modelMapper;
    
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
    public ResponseEntity<?> saveNotificacao(@Valid @RequestBody NotificacaoDTO notificacaoDTO) {        
        Notificacao notificacao = modelMapper.map(notificacaoDTO, Notificacao.class);
        notificationService.saveNotificacao(notificacao);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificacaoById(@PathVariable Long id) {
        Optional<Notificacao> optionalDeletedNotificacao = notificationService.deleteById(id);

        if (optionalDeletedNotificacao.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.noContent().build();
    }
    
}
