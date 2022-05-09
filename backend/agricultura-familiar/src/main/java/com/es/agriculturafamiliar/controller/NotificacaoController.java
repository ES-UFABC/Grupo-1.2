package com.es.agriculturafamiliar.controller;

import javax.validation.Valid;

import com.es.agriculturafamiliar.dto.request.NotificacaoCreationRequest;
import com.es.agriculturafamiliar.dto.response.NotificacaoResponse;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.service.NotificacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private NotificacaoService notificationService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "40") int size) {
    	Pageable paging = PageRequest.of(page, size);

    	Page<NotificacaoResponse> notificacoes = notificationService.findAll(paging)
    			.map(notificacao -> modelMapper.map(notificacao, NotificacaoResponse.class));
        
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findNotificacaoById(@PathVariable Long id)  {
        Notificacao notificacao = notificationService.findNotificacaoById(id);
        NotificacaoResponse notificacaoResponse = modelMapper.map(notificacao, NotificacaoResponse.class);
        return ResponseEntity.ok(notificacaoResponse);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> saveNotificacao(@Valid @RequestBody NotificacaoCreationRequest notificacaoDTO) {        
        Notificacao notificacao = modelMapper.typeMap(NotificacaoCreationRequest.class, Notificacao.class)
        		.addMapping(NotificacaoCreationRequest::getAdminId, (dest, v) -> dest.getAdministrador().setId((Long) v))
        		.map(notificacaoDTO);
        
        notificationService.saveNotificacao(notificacao);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteNotificacaoById(@PathVariable Long id) {
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateNotificacoa(@PathVariable Long id, @RequestBody NotificacaoCreationRequest notificacao) {
        Notificacao notificacaoConvertida = modelMapper.map(notificacao, Notificacao.class);
        Notificacao notificacaoAtualizada = notificationService.update(notificacaoConvertida, id);        
        return ResponseEntity.ok(notificacaoAtualizada);
    }
    
}
