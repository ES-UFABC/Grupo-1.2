package com.es.agriculturafamiliar.entity.produtor;

import lombok.Data;
import javax.persistence.Entity;

@Entity
@Data
public class TipoProducao {

    private Integer id;
    private String nome;
}
