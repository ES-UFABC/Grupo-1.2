package com.es.agriculturafamiliar.constants;

public enum RoleType {
    ADMIN("admin"),
    PRODUTOR("produtor"),
    CONSUMIDOR("consumidor");

    private String roleName;

    public static RoleType valueOfIgnoreCase(String value) {
        return valueOf(value.toUpperCase());
   }

    private RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getLabel() {
        return roleName;
    }
    
}
