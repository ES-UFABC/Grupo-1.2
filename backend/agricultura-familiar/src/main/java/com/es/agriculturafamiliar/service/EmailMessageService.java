package com.es.agriculturafamiliar.service;

import javax.mail.internet.MimeMessage;

import com.es.agriculturafamiliar.config.email.AsyncMessageSenderFactory;
import com.es.agriculturafamiliar.entity.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailMessageService implements AsyncMessageService<Email> {

    private AsyncMessageSenderFactory<Email, JavaMailSender, MimeMessage> asyncMessageSenderFactory;
    private JavaMailSender mailSender;

    @Autowired
    public EmailMessageService(AsyncMessageSenderFactory<Email, JavaMailSender, MimeMessage> asyncMessageSenderFactory, JavaMailSender mailSender) {        
        this.asyncMessageSenderFactory = asyncMessageSenderFactory;
        this.mailSender = mailSender;
    }
    
    @Async
    @Override
    public void sendMessage(Email data) {
        try{
            MimeMessage constructedMessage = asyncMessageSenderFactory.createAsyncMessageSender(data, mailSender);
            mailSender.send(constructedMessage);
        } catch (Exception e){
            log.debug("ERRO AO ENVIAR COMUNICAÇÃO DE CADASTRO: {}", e.getMessage());
        }

    }

}
