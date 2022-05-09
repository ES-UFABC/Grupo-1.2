package com.es.agriculturafamiliar.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.es.agriculturafamiliar.dto.request.AdminRegistrationRequest;
import com.es.agriculturafamiliar.dto.request.RegenerateConfirmationCodeRequest;
import com.es.agriculturafamiliar.dto.request.SignUpConfirmationRequest;
import com.es.agriculturafamiliar.dto.request.UserCredentialsRequest;
import com.es.agriculturafamiliar.entity.Administrador;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.service.AdministradorService;
import com.es.agriculturafamiliar.service.TokenAuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final ModelMapper modelMapper;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final AdministradorService administradorService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentialsRequest userCredentialsDTO) {
        User user = modelMapper.map(userCredentialsDTO, User.class);        
        JwtToken jwtToken = tokenAuthenticationService.authenticate(user);
        return ResponseEntity.ok(jwtToken);        
    }

    @ApiOperation(value = "Endpoint usado para cadastro de administradores - temporário")
    @PostMapping(value="/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AdminRegistrationRequest adminRegistration) {        
    	Administrador admin = modelMapper.map(adminRegistration, Administrador.class);
        administradorService.save(admin);       
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PostMapping("/enable-account")
    public ResponseEntity<?> confirmSignUp(@RequestBody SignUpConfirmationRequest signUpConfirmation) {
    	tokenAuthenticationService.enableAccount(signUpConfirmation.getEmail(), signUpConfirmation.getToken());
    	return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/regenerate-confirmation-code")
    public ResponseEntity<?> regenerateConfirmationCode(@RequestBody RegenerateConfirmationCodeRequest regenerateConfirmationCode) {
    	tokenAuthenticationService.regenerateConfirmationCode(regenerateConfirmationCode.getEmail());
    	return ResponseEntity.noContent().build();
    }
}
