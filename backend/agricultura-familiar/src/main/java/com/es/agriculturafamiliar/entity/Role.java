package com.es.agriculturafamiliar.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.es.agriculturafamiliar.constants.RoleType;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    

    @Override
    public String getAuthority() {
        return role.getLabel();
    }
    
}
