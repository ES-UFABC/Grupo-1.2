package com.es.agriculturafamiliar.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;    
    
}
