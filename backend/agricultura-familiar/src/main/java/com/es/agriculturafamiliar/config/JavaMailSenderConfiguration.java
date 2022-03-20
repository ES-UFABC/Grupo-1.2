package com.es.agriculturafamiliar.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class JavaMailSenderConfiguration {
    @Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private Integer port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String mailSmtpAuth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String mailTransportProtocol;
	
    @Value("${spring.mail.properties.mail.transport.debug}")
    private String mailDebug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setPort(port);
		
		javaMailSenderImpl.setUsername(username);
		javaMailSenderImpl.setPassword(password);
		
		Properties emailProperties = javaMailSenderImpl.getJavaMailProperties();
		emailProperties.put("mail.transport.protocol", mailTransportProtocol);
		emailProperties.put("mail.smtp.auth", mailSmtpAuth);
		emailProperties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
		emailProperties.put("mail.debug", mailDebug);
        		
		return javaMailSenderImpl;
    }
    
}
