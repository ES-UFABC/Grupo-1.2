package com.es.agriculturafamiliar.entity.consumidorPedido;

import com.es.agriculturafamiliar.entity.cadastroconsumidor.CadastroConsumidorEntity;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "consumidor_pedido")
@NoArgsConstructor
public class ConsumidorPedidoEntity {
    @Id
    @Column(name = "id")
    private String id;
    @OneToOne
    @JoinColumn(name = "cpf", referencedColumnName = "id_consumidor")
    private CadastroConsumidorEntity idConsumidor;
    @OneToOne
    @JoinColumn(name = "cpf_ou_cnpj", referencedColumnName = "id_produtor")
    private String idProdutor;
    @Column(name = "pedido")
    private String pedido;
}
