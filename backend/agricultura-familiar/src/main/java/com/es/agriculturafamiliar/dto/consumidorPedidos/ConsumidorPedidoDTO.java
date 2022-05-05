package com.es.agriculturafamiliar.dto.consumidorPedidos;

import com.es.agriculturafamiliar.dto.cadastroConsumidor.CadastroConsumidorDtoIn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsumidorPedidoDTO {
    @NotBlank(message = "idConsumidor deve ser preenchido")
    private CadastroConsumidorDtoIn idConsumidor;

    @NotBlank(message = "idProdutor deve ser preenchido")
    private String idProdutor;

    @NotBlank(message = "pedido deve ser preenchido")
    private String pedido;
}
