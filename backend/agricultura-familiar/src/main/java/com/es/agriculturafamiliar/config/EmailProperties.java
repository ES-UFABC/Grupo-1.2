package com.es.agriculturafamiliar.config;

import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Getter
public class EmailProperties {

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
    
}
