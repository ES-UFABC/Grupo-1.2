package com.es.agriculturafamiliar.service;

import com.es.agriculturafamiliar.entity.Administrador;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.exception.UserUnauthorizedException;
import com.es.agriculturafamiliar.repository.NotificacaoRepository;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificacaoService {
    
    private final NotificacaoRepository notificacaoRepository;
    private final AdministradorService administradorService;
    
    public Page<Notificacao> findAll(Pageable pageable) {
        return notificacaoRepository.findAll(pageable);
    }

    public Notificacao findNotificacaoById(Long id) {
        return notificacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum notificação com o id fornecido encontrada"));
    }

    public Notificacao saveNotificacao(Notificacao notificacao) throws ResourceNotFoundException {
    	Administrador administrador = administradorService.findById(notificacao.getAdministrador().getId());
    	notificacao.setAdministrador(administrador);
    	notificacao.setDataPublicacao(LocalDateTime.now());
    	
        Notificacao savedNotificacao = notificacaoRepository.save(notificacao);
        return savedNotificacao;
    }

    public Notificacao deleteById(Long id) {
        Notificacao notificacaoToBeDeletedOpt = findNotificacaoById(id);        
        notificacaoRepository.deleteById(id);        
        return notificacaoToBeDeletedOpt;
    }

    public Notificacao update(Notificacao notificacao, Long id) {
        Notificacao notificacaoQueriedOptional = findNotificacaoById(id);        
        notificacao.setId(id);
        notificacao.setDataPublicacao(notificacaoQueriedOptional.getDataPublicacao());
        Notificacao updatedNotificacao = notificacaoRepository.save(notificacao);
        return updatedNotificacao;
    }
     
}
