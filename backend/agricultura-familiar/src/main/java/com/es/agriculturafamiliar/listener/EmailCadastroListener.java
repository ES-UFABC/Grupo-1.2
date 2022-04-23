package com.es.agriculturafamiliar.listener;

import java.util.Map;

import com.es.agriculturafamiliar.config.EmailProperties;
import com.es.agriculturafamiliar.constants.TemplateType;
import com.es.agriculturafamiliar.entity.Email;
import com.es.agriculturafamiliar.event.EmailCadastroEvent;
import com.es.agriculturafamiliar.service.AsyncMessageService;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@AllArgsConstructor
@Component
public class EmailCadastroListener {
    private final AsyncMessageService<Email> asyncMessageService;
    private final EmailProperties emailProperties;

    @EventListener
    @Async
    public void onEmailCadastroEvent(EmailCadastroEvent emailCadastroEvent) {
    	log.debug("Evento EmailCadastroEvent capturado, enviando email");
        String name = emailCadastroEvent.getName();
        String to = emailCadastroEvent.getToEmail();

        Map<String, Object> context = Map.of("name", name);
        Email email = Email.builder()
            .subject("Seu cadastro foi realizado com sucesso!")
            .variables(context)
            .to(to)
            .from(emailProperties.getUsername())
            .templateName(TemplateType.EMAIL_SIGNUP_DEFAULT)
            .build();
        
        asyncMessageService.sendMessage(email);
            
    }
    
}
