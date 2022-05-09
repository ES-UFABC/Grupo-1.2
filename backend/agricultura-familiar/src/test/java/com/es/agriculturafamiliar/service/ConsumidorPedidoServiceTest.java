package com.es.agriculturafamiliar.service;

import com.es.agriculturafamiliar.controller.mapper.ConsumidorPedidoEntityMapper;
import com.es.agriculturafamiliar.dto.request.ConsumidorPedidoRequest;
import com.es.agriculturafamiliar.entity.consumidorPedido.ConsumidorPedidoEntity;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;
import com.es.agriculturafamiliar.repository.consumidorPedido.jpa.ConsumidorPedidoRepository;
import com.es.agriculturafamiliar.service.consumidorPedido.ConsumidorPedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ConsumidorPedidoServiceTest {

    @InjectMocks
    private ConsumidorPedidoService useCase;

    @Mock
    private ConsumidorPedidoRepository repository;

    @Mock
    private ConsumidorPedidoEntityMapper consumidorPedidoEntityMapper;

    @Mock
    private CadastroConsumidorRepository cadastroConsumidorRepository;

    @Mock
    private ProdutorRepository produtorRepository;

    @Test
    public void erroAoCadastrar_idConsumidorNaoExiste() {
        Mockito.when(cadastroConsumidorRepository.consultaPorChave("572.355.330-56")).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> useCase.cadastraPedido(
                ConsumidorPedidoRequest.builder().idConsumidor("572.355.330-56").build()));
    }

    @Test
    public void erroAoCadastrar_idProdutorNaoExiste() {
        Mockito.when(cadastroConsumidorRepository.consultaPorChave("572.355.330-56")).thenReturn(
                Optional.of(CadastroConsumidorDomain.builder().build()));
        Mockito.when(produtorRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> useCase.cadastraPedido(
                ConsumidorPedidoRequest.builder().idConsumidor("572.355.330-56").idProdutor(1L).build()));
    }

    @Test
    public void sucessoAoCadastrar() {
        Mockito.when(cadastroConsumidorRepository.consultaPorChave("572.355.330-56")).thenReturn(
                Optional.of(CadastroConsumidorDomain.builder().build()));
        Mockito.when(produtorRepository.findById(1L)).thenReturn(Optional.of(Produtor.builder().build()));
        Mockito.when(consumidorPedidoEntityMapper.toEntity(mockConsumidorPedidoDto())).thenReturn(mockConsumidorPedidoEntity());
        Mockito.when(repository.save(mockConsumidorPedidoEntity())).thenReturn(mockConsumidorPedidoEntity());
        useCase.cadastraPedido(mockConsumidorPedidoDto());
        Mockito.verify(repository, Mockito.times(1)).save(
                mockConsumidorPedidoEntity());
    }

    private ConsumidorPedidoRequest mockConsumidorPedidoDto() {
        return ConsumidorPedidoRequest.builder()
                .idConsumidor("572.355.330-56")
                .idProdutor(1L)
                .pedido("soja")
                .build();
    }

    private ConsumidorPedidoEntity mockConsumidorPedidoEntity() {
        return ConsumidorPedidoEntity.builder()
//                .id(1)
                .idConsumidor("572.355.330-56")
                .idProdutor(1L)
                .pedido("soja")
                .build();
    }
}
