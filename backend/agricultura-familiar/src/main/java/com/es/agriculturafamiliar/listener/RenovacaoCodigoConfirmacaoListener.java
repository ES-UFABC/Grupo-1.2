package com.es.agriculturafamiliar.listener;

import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.es.agriculturafamiliar.config.EmailProperties;
import com.es.agriculturafamiliar.config.FrontEndProperties;
import com.es.agriculturafamiliar.entity.Email;
import com.es.agriculturafamiliar.enums.TemplateType;
import com.es.agriculturafamiliar.event.RenovacaoCodigoConfirmacaoEvent;
import com.es.agriculturafamiliar.service.AsyncMessageService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RenovacaoCodigoConfirmacaoListener {
    private final AsyncMessageService<Email> asyncMessageService;
    private final EmailProperties emailProperties;
    private final FrontEndProperties frontEndProperties;

    @EventListener
    @Async
    public void onEmailCadastroEvent(RenovacaoCodigoConfirmacaoEvent renovacaoCodigoConfirmacaoEvent) {

        String to = renovacaoCodigoConfirmacaoEvent.getToEmail();
        String codigoConfirmacao = renovacaoCodigoConfirmacaoEvent.getCodigoConfirmacao();
        String emailConfirmationEndpoint = frontEndProperties.getEmailConfirmationEndpoint();
        String baseFrontEndUrl = frontEndProperties.getBaseUrl();
        String emailConfirmationUrl = baseFrontEndUrl + emailConfirmationEndpoint;

        Map<String, Object> context = Map.of(
        		"confirmationCode", codigoConfirmacao,
        		"confirmacaoCadastroFrontEnd", emailConfirmationUrl);;
        
        Email email = Email.builder()
            .subject("Seu código de confirmação foi recriado!")
            .variables(context)
            .to(to)
            .from(emailProperties.getUsername())
            .templateName(TemplateType.EMAIL_SIGNUP_CONFIRMATION_CODE_RENEWED)
            .build();
        
        asyncMessageService.sendMessage(email);
            
    }
}
