package com.es.agriculturafamiliar.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notificacao {

    private Long id; 
    private String assunto;
    private String mensagem;
    private LocalDateTime dataPublicacao;
    
}
