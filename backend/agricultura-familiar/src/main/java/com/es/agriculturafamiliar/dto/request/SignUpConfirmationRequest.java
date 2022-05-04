package com.es.agriculturafamiliar.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.es.agriculturafamiliar.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpConfirmationRequest {
	@NotBlank
	private String token;
	@Email
	@NotBlank
	private String email;

}
