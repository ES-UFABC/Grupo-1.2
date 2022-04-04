package com.es.agriculturafamiliar.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.es.agriculturafamiliar.constants.RoleTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleTypes role;
    
}
