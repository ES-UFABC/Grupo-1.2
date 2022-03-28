package com.es.agriculturafamiliar.models.cadastroconsumidor;

import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;


public class CadastroConsumidorUseCaseTest {
    @Mock
    private CadastroConsumidorUseCase usecase;

    @InjectMocks
    private CadastroConsumidorRepository repository;

    @Test
    public void erroAoCadastrar_consumidorJaExiste() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.of(mockCadastroConsumidor()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> usecase.cadastraConsumidor(mockCadastroConsumidor()));
    }

    @Test
    public void sucessoAoCadastrar_consumidorCriadoComSucesso() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.empty());
        usecase.cadastraConsumidor(mockCadastroConsumidor());
        Mockito.verify(repository.salvar(mockCadastroConsumidor()), Mockito.times(1));
    }

    private CadastroConsumidorDomain mockCadastroConsumidor() {
        return CadastroConsumidorDomain.builder()
                .cpf("11111111111")
                .build();
    }

}
