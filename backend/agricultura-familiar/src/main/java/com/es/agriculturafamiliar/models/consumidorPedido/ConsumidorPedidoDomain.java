package com.es.agriculturafamiliar.models.consumidorPedido;

import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumidorPedidoDomain {
    private CadastroConsumidorDomain idConsumidor;
    private String idProdutor;
    private String pedido;
}
