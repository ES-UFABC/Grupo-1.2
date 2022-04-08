package com.es.agriculturafamiliar.entity.cadastroconsumidor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.es.agriculturafamiliar.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "consumidor")
@NoArgsConstructor
public class CadastroConsumidorEntity {
    @Id
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private String telefone;
    @OneToMany(mappedBy= "consumidor", cascade = CascadeType.ALL)
    private List<EnderecoEntity> endereco;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @PrePersist
	private void prePersist() {        
	    if(endereco != null) {
	    	endereco.forEach(e -> e.setConsumidor(this));
	    };
	}
}
