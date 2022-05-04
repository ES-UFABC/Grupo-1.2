package com.es.agriculturafamiliar.dto.response;

import com.es.agriculturafamiliar.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private RoleType role;
    
}
