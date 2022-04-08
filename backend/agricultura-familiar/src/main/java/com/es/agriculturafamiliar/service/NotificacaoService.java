package com.es.agriculturafamiliar.service;

import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.NotificacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.es.agriculturafamiliar.constants.Constants;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;
    
    public Page<Notificacao> findAll() {
        return notificacaoRepository.findAll(Pageable.ofSize(Constants.DEFAULT_PAGE_SIZE));
    }

    public Notificacao findNotificacaoById(Long id) {
        return notificacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum notificação com o id fornecido encontrada"));
    }

    public Notificacao saveNotificacao(Notificacao notificacao) {
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
