package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.repository.NotificacaoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificacaoServiceTests {
    
    @InjectMocks
    private static NotificacaoService notificacaoService;

    @Mock
    private static NotificacaoRepository notificacaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveNotificacao_shouldReturnSavedNotification_whenSuccessful() {
        Notificacao notificacao = Notificacao.builder()
            .assunto("Hola")
            .mensagem("Que passa")
            .build();
        
        Notificacao returnedSavedNotificacao = Notificacao.builder()
            .assunto(notificacao.getAssunto())
            .mensagem(notificacao.getMensagem())
            .id(3123l)
            .dataPublicacao(LocalDateTime.now())
            .build();

        when(notificacaoRepository.save(any(Notificacao.class)))
            .thenReturn(returnedSavedNotificacao);
        
        Optional<Notificacao> savedNotificacaoOptional = notificacaoService.saveNotificacao(notificacao);
        
        assertTrue(savedNotificacaoOptional.isPresent());

        Notificacao savedNotificacao = savedNotificacaoOptional.get();

        assertEquals(notificacao.getAssunto(), savedNotificacao.getAssunto());
        assertEquals(notificacao.getMensagem(), savedNotificacao.getMensagem());
        assertNotNull(savedNotificacao.getDataPublicacao());
        assertNotNull(savedNotificacao.getId());
    }

    @Test
    public void saveNotificacao_shouldReturnEmptyOptional_whenNotSuccessful() {
        Notificacao notificacao = Notificacao.builder()
            .assunto("Hola")
            .mensagem("Que passa")
            .build();
        
        
        when(notificacaoRepository.save(any(Notificacao.class)))
            .thenReturn(null);
        
        Optional<Notificacao> savedNotificacaoOptional = notificacaoService.saveNotificacao(notificacao);

        assertTrue(savedNotificacaoOptional.isEmpty());
    }
  
    @Test
    public void findNotificacaoById_shouldReturnEmptyOptional_whenNotSuccessful() {
        when(notificacaoRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());
        
        Optional<Notificacao> savedNotificacaoOptional = notificacaoService.findNotificacaoById(33l);

        assertTrue(savedNotificacaoOptional.isEmpty());
    }
    
    @Test
    public void findNotificacaoById_shouldReturnSavedNotification_whenSuccessful() {
        
        Notificacao queriedNotificacao = Notificacao.builder()
            .assunto("Notificacao")
            .mensagem("Estou notificando sobre o")
            .id(3123l)
            .dataPublicacao(LocalDateTime.now())
            .build();

        when(notificacaoRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(queriedNotificacao));
        
        Optional<Notificacao> returnedNotificacaoOptional = notificacaoService.findNotificacaoById(32l);
        
        assertTrue(returnedNotificacaoOptional.isPresent());

        Notificacao returnedNotificacao = returnedNotificacaoOptional.get();

        assertEquals(queriedNotificacao.getAssunto(), returnedNotificacao.getAssunto());
        assertEquals(queriedNotificacao.getMensagem(), returnedNotificacao.getMensagem());
        assertEquals(queriedNotificacao.getDataPublicacao(), returnedNotificacao.getDataPublicacao());
        assertEquals(queriedNotificacao.getId(), returnedNotificacao.getId());
    }

    @Test
    public void deleteNotificacaoById_shouldReturnEmptyOptional_whenNotSuccessful() {
        when(notificacaoRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());
        
        Optional<Notificacao> savedNotificacaoOptional = notificacaoService.deleteById(33l);

        assertTrue(savedNotificacaoOptional.isEmpty());
    }
    
    @Test
    public void deleteNotificacaoById_shouldReturnSavedNotification_whenSuccessful() {        
        Notificacao queriedNotificacao = Notificacao.builder()
            .assunto("Notificacao")
            .mensagem("Estou notificando sobre o")
            .id(3123l)
            .dataPublicacao(LocalDateTime.now())
            .build();

        when(notificacaoRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(queriedNotificacao));
        
        Optional<Notificacao> returnedNotificacaoOptional = notificacaoService.deleteById(32l);
        
        assertTrue(returnedNotificacaoOptional.isPresent());

        Notificacao returnedNotificacao = returnedNotificacaoOptional.get();

        assertEquals(queriedNotificacao.getAssunto(), returnedNotificacao.getAssunto());
        assertEquals(queriedNotificacao.getMensagem(), returnedNotificacao.getMensagem());
        assertEquals(queriedNotificacao.getDataPublicacao(), returnedNotificacao.getDataPublicacao());
        assertEquals(queriedNotificacao.getId(), returnedNotificacao.getId());
    }

    @Test
    public void updateNotificacaoById_shouldReturnEmptyOptional_whenNotSuccessful() {
        Notificacao updateNotificacao = Notificacao.builder()
            .assunto("Notificacao - atualizada")
            .mensagem("Estou atualizado")            
            .build();

        Optional<Notificacao> savedNotificacaoOptional = notificacaoService.update(updateNotificacao, 33l);

        assertTrue(savedNotificacaoOptional.isEmpty());
    }
    
    @Test
    public void updateNotificacaoById_shouldReturnSavedNotification_whenSuccessful() {        
        Notificacao queriedNotificacao = Notificacao.builder()
            .assunto("Notificacao")
            .mensagem("Estou notificando sobre o")
            .id(3123l)
            .dataPublicacao(LocalDateTime.now())
            .build();

        Notificacao updateNotificacao = Notificacao.builder()
            .assunto("Notificacao - atualizada")
            .mensagem("Estou atualizado")
            .build();

        when(notificacaoRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(queriedNotificacao));

        when(notificacaoRepository.save(any(Notificacao.class)))
            .thenReturn(updateNotificacao);
        
        Optional<Notificacao> returnedNotificacaoOptional = notificacaoService.update(updateNotificacao, queriedNotificacao.getId());
        
        assertTrue(returnedNotificacaoOptional.isPresent());

        Notificacao returnedNotificacao = returnedNotificacaoOptional.get();

        assertEquals(updateNotificacao.getAssunto(), returnedNotificacao.getAssunto());
        assertEquals(updateNotificacao.getMensagem(), returnedNotificacao.getMensagem());
        assertEquals(queriedNotificacao.getDataPublicacao(), returnedNotificacao.getDataPublicacao());
        assertEquals(queriedNotificacao.getId(), returnedNotificacao.getId());
    }


    
}
