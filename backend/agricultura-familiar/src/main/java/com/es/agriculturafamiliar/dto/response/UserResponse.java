package com.es.agriculturafamiliar.dto.response;

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
public class UserResponse {
	private boolean enabled;
	private Set<Role> roles;
}
