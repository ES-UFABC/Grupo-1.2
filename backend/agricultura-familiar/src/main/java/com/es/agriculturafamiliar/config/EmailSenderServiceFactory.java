package com.es.agriculturafamiliar.config;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.es.agriculturafamiliar.entity.Email;
import com.es.agriculturafamiliar.exception.SendMessageException;

import org.thymeleaf.context.Context;
import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;

@Component
public class EmailSenderServiceFactory extends AsyncMessageSenderFactory<Email, JavaMailSender, MimeMessage> {
    
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    public EmailSenderServiceFactory(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public MimeMessage createAsyncMessageSender(Email data, JavaMailSender mailSender) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        configureMimeMessage(data, mimeMessage);
        return mimeMessage;
    }

	private void configureMimeMessage(Email emailData, MimeMessage mimeMessage) {
		try {
            Map<String, Object> variables = emailData.getVariables();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = getContext(variables);
			String html = springTemplateEngine.process(emailData.getTemplateName().getLabel(), context);
			configureMimeMessageHelper(mimeMessageHelper, html, emailData);
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new SendMessageException("An error occurred while sending email, please try again");
		}
	}

	private void configureMimeMessageHelper(MimeMessageHelper mimeMessageHelper, String html, Email emailData) throws MessagingException, UnsupportedEncodingException {
        boolean isHtmlBody = true;
		mimeMessageHelper.setTo(emailData.getTo());
		mimeMessageHelper.setText(html, isHtmlBody);
		mimeMessageHelper.setSubject(emailData.getSubject());
		mimeMessageHelper.setFrom(emailData.getFrom(), emailData.getSubject());
	}

	private Context getContext(Map<String, Object> contextMapVariables) {
		Context context = new Context();        
		context.setVariables(contextMapVariables);
		return context;
	}
    
}
