package com.es.agriculturafamiliar.listener;

import java.util.Map;

import com.es.agriculturafamiliar.constants.TemplateType;
import com.es.agriculturafamiliar.dto.EmailDTO;
import com.es.agriculturafamiliar.event.DummyEvent;
import com.es.agriculturafamiliar.service.AsyncMessageService;
import com.es.agriculturafamiliar.service.EmailMessageService;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DummyListener {
    private final AsyncMessageService<EmailDTO> asyncMessageService;

    @EventListener
    @Async
    public void onDummyEvent(DummyEvent dummyEvent) {
        String name = dummyEvent.getName();
        String to = dummyEvent.getToEmail();

        Map<String, Object> context = Map.of("name", name);
        EmailDTO emailDTO = EmailDTO.builder()
            .subject("testEmail")
            .variables(context)
            .to(to)
            .from("muda.es.soft.suporte@gmail.com")            
            .templateName(TemplateType.REGISTRATION_PRODUTOR)
            .build(); 
                     
            asyncMessageService.sendMessage(emailDTO);
            
    }
    
}
