package com.es.agriculturafamiliar.dto;

import com.es.agriculturafamiliar.constants.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegenerateConfirmationCodeDTO {
	private String email;
}
