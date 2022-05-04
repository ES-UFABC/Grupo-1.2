package com.es.agriculturafamiliar.enums;

import java.util.Arrays;

public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    PRODUTOR("ROLE_PRODUTOR"),
    CONSUMIDOR("ROLE_CONSUMIDOR");

    private String roleName;

    public static RoleType valueOfIgnoreCase(String value) {
        return Arrays.stream(RoleType.values())
            .filter(v -> v.roleName.equals(value))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Role não encontrada"));
   }

    private RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getLabel() {
        return roleName;
    }
    
}
