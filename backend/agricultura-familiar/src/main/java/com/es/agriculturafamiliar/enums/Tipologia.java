package com.es.agriculturafamiliar.enums;

import lombok.Getter;

@Getter
public enum Tipologia {
    DESCONHECIDA(1, "Desconhecida"),
    AGROINDUSTRIA(2, "Agroindústria"),
    APICULTURA(3, "Apicultura"),
    CEREAIS(4, "Cereais"),
    FRUTICULTURA_PERENE(5, "Fruticultura perene"),
    HORTALICAS(6, "Hortaliças"),
    LATICINIOS_E_DERIVADOS(7, "Laticínios e derivados"),
    PROTEINA(8, "Proteína de origem animal"),
    RAIZES_E_TURBERCULOS(9, "Raízes e turbéculos"),
    REFEICOES(10, "Refeições"),
    SEMENTES_E_MUDAS(11, "Sementes e mudas"),
    FRUTICULTURA_CICLOS(12, "Fruticultura ciclo médio e curto");

    private Integer cod;
    private String descricao;

    private Tipologia(Integer cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Tipologia toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(Tipologia x : Tipologia.values()){
            if(x.getCod() == cod){
                return x;
            }
        }

        throw new IllegalArgumentException("Id de Tipologia inválido: " + cod);
    }
}
