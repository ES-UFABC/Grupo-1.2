package com.es.agriculturafamiliar.entity;

import java.util.Map;

import com.es.agriculturafamiliar.enums.TemplateType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
    private TemplateType templateName;
    private String to;
    private String subject;
    private String from;  
    private Map<String, Object> variables;  
    
}
