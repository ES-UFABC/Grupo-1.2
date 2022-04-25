package com.es.agriculturafamiliar.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoResponse {
    
    private Long id; 
    private String assunto;
    private String mensagem;
    
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dataPublicacao;
    private AdministradorResponse administrador;
}
