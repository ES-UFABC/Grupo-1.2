package com.es.agriculturafamiliar.listener;

import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.config.EmailProperties;
import com.es.agriculturafamiliar.config.FrontEndProperties;
import com.es.agriculturafamiliar.constants.TemplateType;
import com.es.agriculturafamiliar.entity.Email;
import com.es.agriculturafamiliar.event.EmailCadastroConfirmacaoPendenteEvent;
import com.es.agriculturafamiliar.event.EmailCadastroEvent;
import com.es.agriculturafamiliar.service.AsyncMessageService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmailCadastroConfirmacaoPendenteListener {
    private final AsyncMessageService<Email> asyncMessageService;
    private final EmailProperties emailProperties;
    private final FrontEndProperties frontEndProperties;

    @EventListener
    @Async
    public void onEmailCadastroConfirmacaoPendenteEvent(EmailCadastroConfirmacaoPendenteEvent emailCadastroConfirmacaoPendenteEvent) {
        String nome = emailCadastroConfirmacaoPendenteEvent.getName();
        String to = emailCadastroConfirmacaoPendenteEvent.getToEmail();
        String codigoConfirmacao = emailCadastroConfirmacaoPendenteEvent.getCodigoConfirmacao();
        String emailConfirmationEndpoint = frontEndProperties.getEmailConfirmationEndpoint();

        Map<String, Object> context = Map.of(
        		"name", nome, 
        		"confirmationCode", codigoConfirmacao,
        		"confirmacao-cadastro-front-end", emailConfirmationEndpoint);
        
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
