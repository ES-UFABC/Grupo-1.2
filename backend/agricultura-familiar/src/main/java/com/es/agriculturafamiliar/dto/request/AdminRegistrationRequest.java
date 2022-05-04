package com.es.agriculturafamiliar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationRequest {
	
	private UserCredentialsRequest user;
	private String nome;

}
