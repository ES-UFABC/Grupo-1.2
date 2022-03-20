package com.es.agriculturafamiliar.constants;

public enum TemplateType {
    REGISTRATION_PRODUTOR("registro-produtor");

    private TemplateType(String label) {
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }
    
}
