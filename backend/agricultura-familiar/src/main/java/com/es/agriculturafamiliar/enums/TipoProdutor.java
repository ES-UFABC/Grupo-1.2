package com.es.agriculturafamiliar.enums;

import lombok.Getter;

@Getter
public enum TipoProdutor {
    INDIVIDUAL(1, "Individual"),
    COLETIVO(2, "Coletivo");

    private Integer cod;
    private String descricao;

    private TipoProdutor(Integer cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TipoProdutor toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(TipoProdutor x : TipoProdutor.values()){
            if(x.getCod() == cod){
                return x;
            }
        }

        throw new IllegalArgumentException("Id de TipoProdutor inválido: " + cod);
    }
}
