package com.es.agriculturafamiliar.enums;

import lombok.Getter;

@Getter
public enum TipoEndereco {
    PRODUCAO(1, "Produção"),
    COMERCIALIZACAO(2, "Comecialização");

    private Integer cod;
    private String descricao;

    private TipoEndereco(Integer cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TipoEndereco toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(TipoEndereco x : TipoEndereco.values()){
            if(x.getCod() == cod){
                return x;
            }
        }

        throw new IllegalArgumentException("Id de TipoEndereco inválido: " + cod);
    }
}
