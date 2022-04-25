package com.es.agriculturafamiliar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationDTO {
	
	private UserCredentialsDTO user;
	private String nome;

}
