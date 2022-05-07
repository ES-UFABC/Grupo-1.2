package com.es.agriculturafamiliar.config.email;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class EmailConfiguration {
   private EmailProperties emailProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(emailProperties.getHost());
		javaMailSenderImpl.setPort(emailProperties.getPort());
		
		javaMailSenderImpl.setUsername(emailProperties.getUsername());
		javaMailSenderImpl.setPassword(emailProperties.getPassword());
	
		Properties properties = javaMailSenderImpl.getJavaMailProperties();
		properties.put("mail.transport.protocol", emailProperties.getMailTransportProtocol());
		properties.put("mail.smtp.auth", emailProperties.getMailSmtpAuth());
		properties.put("mail.smtp.starttls.enable", emailProperties.getMailSmtpStarttlsEnable());
		properties.put("mail.debug", emailProperties.getMailDebug());
        		
		return javaMailSenderImpl;
    }
    
}
