package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.es.agriculturafamiliar.entity.Administrador;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.NotificacaoRepository;

@ExtendWith(MockitoExtension.class)
public class NotificacaoServiceTests {
	
	@InjectMocks
	private NotificacaoService notificacaoService;
    
	
    @Mock
    private NotificacaoRepository notificacaoRepository;
    
    @Mock
    private AdministradorService administradorService;


    @Test
    public void saveNotificacao_shouldReturnSavedNotification_whenSuccessful() {
    	Administrador adm = Administrador.builder().id(1L).build();
    	
        Notificacao notificacao = Notificacao.builder()
            .assunto("Hola")
            .mensagem("Que passa")
            .administrador(adm)
            .build();
        
        Notificacao returnedSavedNotificacao = Notificacao.builder()
            .assunto(notificacao.getAssunto())
            .mensagem(notificacao.getMensagem())
            .id(3123l)
            .administrador(adm)
            .dataPublicacao(LocalDateTime.now())
            .build();

        when(notificacaoRepository.save(any(Notificacao.class)))
            .thenReturn(returnedSavedNotificacao);
        when(administradorService.findById(anyLong()))
        	.thenReturn(adm);
        
        Notificacao savedNotificacao = notificacaoService.saveNotificacao(notificacao);

        assertEquals(notificacao.getAssunto(), savedNotificacao.getAssunto());
        assertEquals(notificacao.getMensagem(), savedNotificacao.getMensagem());
        assertNotNull(savedNotificacao.getDataPublicacao());
        assertNotNull(savedNotificacao.getId());
    }

  
    @Test
    public void findNotificacaoById_shouldThrowResourceNotFoundException_whenResourceIsNotFound() {
        when(notificacaoRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> notificacaoService.findNotificacaoById(33l));
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
        
        Notificacao returnedNotificacao = notificacaoService.findNotificacaoById(32l);
    
        assertEquals(queriedNotificacao.getAssunto(), returnedNotificacao.getAssunto());
        assertEquals(queriedNotificacao.getMensagem(), returnedNotificacao.getMensagem());
        assertEquals(queriedNotificacao.getDataPublicacao(), returnedNotificacao.getDataPublicacao());
        assertEquals(queriedNotificacao.getId(), returnedNotificacao.getId());
    }

    @Test
    public void deleteNotificacaoById_shouldThrowResourceNotFoundException_whenResourceIsNotFound() {
        when(notificacaoRepository.findById(any(Long.class)))
            .thenThrow(ResourceNotFoundException.class);       
        
        assertThrows(ResourceNotFoundException.class, () -> notificacaoService.deleteById(33l));        
    }
    
    @Test
    public void deleteNotificacaoById_shouldReturnSavedNotification_whenSuccessful() {        
        Notificacao queriedNotificacao = Notificacao.builder()
            .assunto("Notificacao")
            .mensagem("Estou notificando sobre o")
            .id(3123l)
            .dataPublicacao(LocalDateTime.now())
            .build();

        when(notificacaoRepository.findById(any()))
            .thenReturn(Optional.of(queriedNotificacao));
        
        Notificacao returnedNotificacao = notificacaoService.deleteById(32l);
        
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

            assertThrows(ResourceNotFoundException.class, () -> notificacaoService.update(updateNotificacao, 33l));
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
            
        Notificacao returnedNotificacao = notificacaoService.update(updateNotificacao, queriedNotificacao.getId());
        
        assertEquals(updateNotificacao.getAssunto(), returnedNotificacao.getAssunto());
        assertEquals(updateNotificacao.getMensagem(), returnedNotificacao.getMensagem());
        assertEquals(queriedNotificacao.getDataPublicacao(), returnedNotificacao.getDataPublicacao());
        assertEquals(queriedNotificacao.getId(), returnedNotificacao.getId());
    }


    
}
