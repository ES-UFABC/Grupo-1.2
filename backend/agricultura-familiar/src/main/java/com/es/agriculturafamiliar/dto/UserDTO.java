package com.es.agriculturafamiliar.dto;

import java.util.Set;

import com.es.agriculturafamiliar.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private boolean enabled;
    private Set<Role> roles;
}
