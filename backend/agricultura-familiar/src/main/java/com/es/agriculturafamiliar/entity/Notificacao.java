package com.es.agriculturafamiliar.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank
    private String assunto;
    @NotBlank
    private String mensagem;
    
    @CreationTimestamp
    private LocalDateTime dataPublicacao;
    
    @ManyToOne
    @JoinColumn(name = "administrador_id", referencedColumnName = "id")
    private Administrador administrador;
    
}
