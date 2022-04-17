package com.es.agriculturafamiliar.dto;

import com.es.agriculturafamiliar.constants.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignUpConfirmationDTO {
	private final String token;
	private final String email;

}
