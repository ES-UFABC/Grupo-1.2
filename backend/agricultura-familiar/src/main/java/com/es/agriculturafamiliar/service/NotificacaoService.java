package com.es.agriculturafamiliar.service;

import java.util.Optional;

import com.es.agriculturafamiliar.entity.Notificacao;
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

    public Optional<Notificacao> findNotificacaoById(Long id) {
        return notificacaoRepository.findById(id);
    }

    public Optional<Notificacao> saveNotificacao(Notificacao notificacao) {
        Notificacao savedNotificacao = notificacaoRepository.save(notificacao);
        return Optional.ofNullable(savedNotificacao);        
    }

    public Optional<Notificacao> deleteById(Long id) {
        Optional<Notificacao> notificacaoToBeDeletedOpt = findNotificacaoById(id);        

        if (notificacaoToBeDeletedOpt.isEmpty()) {
            return Optional.empty();
        }

        notificacaoRepository.deleteById(id);        
        return notificacaoToBeDeletedOpt;
    }

    public Optional<Notificacao> update(Notificacao notificacao, Long id) {
        Optional<Notificacao> notificacaoQueriedOptional = findNotificacaoById(id);
        if (!notificacaoQueriedOptional.isPresent()) {
            return Optional.empty();
        }
        notificacao.setId(id);
        notificacao.setDataPublicacao(notificacaoQueriedOptional.get().getDataPublicacao());
        Notificacao updatedNotificacao = notificacaoRepository.save(notificacao);
        return Optional.of(updatedNotificacao);
    }
     
}
