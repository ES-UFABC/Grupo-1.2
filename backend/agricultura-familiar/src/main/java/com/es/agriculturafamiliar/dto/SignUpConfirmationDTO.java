package com.es.agriculturafamiliar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.es.agriculturafamiliar.constants.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpConfirmationDTO {
	@NotBlank
	private String token;
	@Email
	@NotBlank
	private String email;

}
