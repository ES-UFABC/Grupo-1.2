package com.es.agriculturafamiliar.enums;

public enum TemplateType {
    EMAIL_SIGNUP_DEFAULT("default-email-signup"),
	EMAIL_SIGNUP_PENDING_CONFIRMATION("pending-confirmation-signup"),
	EMAIL_SIGNUP_CONFIRMATION_CODE_RENEWED("renewed-confirmation-code");
	
    private TemplateType(String label) {
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }
    
}
