package com.es.agriculturafamiliar.dto.consumidorPedidos;

import com.es.agriculturafamiliar.dto.cadastroConsumidor.CadastroConsumidorDtoIn;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsumidorPedidoDTO {

    @NotNull(message = "idConsumidor deve ser preenchido")
    private String idConsumidor;

    @NotNull(message = "idProdutor deve ser preenchido")
    private Long idProdutor;

    @NotBlank(message = "pedido deve ser preenchido")
    private String pedido;

}
