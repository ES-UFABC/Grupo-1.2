package com.es.agriculturafamiliar.constants;

public enum RoleTypes {
    ADMIN("admin"),
    PRODUTOR("produtor"),
    CONSUMIDOR("consumidor");

    private String label;

    private RoleTypes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
}
