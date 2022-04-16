package com.es.agriculturafamiliar.constants;

public enum TemplateType {
    EMAIL_SIGNUP_DEFAULT("default-email-signup"),
	EMAIL_SIGNUP_PENDING_CONFIRMATION("pending-confirmation-signup");

    private TemplateType(String label) {
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }
    
}
