package com.es.agriculturafamiliar.entity.consumidorPedido;

import com.es.agriculturafamiliar.entity.cadastroconsumidor.CadastroConsumidorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "consumidor_pedido")
@NoArgsConstructor
@Builder
public class ConsumidorPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_consumidor")
    private String idConsumidor;
    @Column(name = "id_produtor")
    private Long idProdutor;
    @Column(name = "pedido")
    private String pedido;

}
