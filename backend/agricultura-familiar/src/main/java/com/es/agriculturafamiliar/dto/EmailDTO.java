package com.es.agriculturafamiliar.dto;

import java.util.Map;

import com.es.agriculturafamiliar.constants.TemplateType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDTO {
    private TemplateType templateName;
    private String to;
    private String subject;
    private String from;  
    private Map<String, Object> variables;  
    
}
