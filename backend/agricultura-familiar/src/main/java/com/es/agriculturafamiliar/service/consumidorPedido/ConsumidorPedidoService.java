package com.es.agriculturafamiliar.service.consumidorPedido;

import com.es.agriculturafamiliar.controller.mapper.ConsumidorPedidoEntityMapper;
import com.es.agriculturafamiliar.dto.consumidorPedidos.ConsumidorPedidoDTO;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;
import com.es.agriculturafamiliar.repository.consumidorPedido.jpa.ConsumidorPedidoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
@AllArgsConstructor
public class ConsumidorPedidoService {

    private final ConsumidorPedidoRepository repository;
    private final ConsumidorPedidoEntityMapper mapper;
    private final CadastroConsumidorRepository cadastroConsumidorRepository;
    private final ProdutorRepository produtorRepository;


    public void cadastraPedido(ConsumidorPedidoDTO dto) {
        log.info("ConsumidorPedidoService - cadastraPedido - idConsumidor: {}, idProdutor {}, pedido: {}",
                dto.getIdConsumidor(), dto.getIdProdutor(), dto.getPedido());

        verificaExistenciaConsumidorEhProdutor(dto);

        var consumidorPedidoEntity = mapper.toEntity(dto);

        repository.save(consumidorPedidoEntity);

        log.info("Cadastro de Pedido realizado com sucesso");
    }

    public void deletaPedido(String idConsumidor, String idProdutor, String pedido) {
        log.info("ConsumidorPedidoService - deletaPedido - idConsumidor: {}, idProdutor {}, pedido: {}",
                idConsumidor, idProdutor, pedido);

        repository.deleteByIdConsumidorAndIdProdutorAndPedido
                (idConsumidor,Float.parseFloat(idProdutor),pedido);

        log.info("Deleção física do Pedido realizada com sucesso");
    }

    private void verificaExistenciaConsumidorEhProdutor(ConsumidorPedidoDTO dto) {
        var verificaExistenciaConsumidor =
                cadastroConsumidorRepository.consultaPorChave(dto.getIdConsumidor());

        if (verificaExistenciaConsumidor.isEmpty()) {
            throw new NoSuchElementException("Consumidor não existe");
        }

        var verificaExistenciaProdutor =
                produtorRepository.findById(dto.getIdProdutor());

        if (verificaExistenciaProdutor.isEmpty()) {
            throw new NoSuchElementException("Produtor não existe");
        }
    }
}
