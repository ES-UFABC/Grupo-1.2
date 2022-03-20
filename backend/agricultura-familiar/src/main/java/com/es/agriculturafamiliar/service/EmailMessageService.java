package com.es.agriculturafamiliar.service;

import javax.mail.internet.MimeMessage;

import com.es.agriculturafamiliar.config.AsyncMessageSenderFactory;
import com.es.agriculturafamiliar.dto.EmailDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageService implements AsyncMessageService<EmailDTO> {

    private AsyncMessageSenderFactory<EmailDTO, JavaMailSender, MimeMessage> asyncMessageSenderFactory;
    private JavaMailSender mailSender;

    @Autowired
    public EmailMessageService(AsyncMessageSenderFactory<EmailDTO, JavaMailSender, MimeMessage> asyncMessageSenderFactory, JavaMailSender mailSender) {        this.asyncMessageSenderFactory = asyncMessageSenderFactory;
        this.mailSender = mailSender;
    }
    
    @Async
    @Override
    public void sendMessage(EmailDTO data) {
        MimeMessage constructedMessage = asyncMessageSenderFactory.createAsyncMessageSender(data, mailSender);
        mailSender.send(constructedMessage);
    }

}
