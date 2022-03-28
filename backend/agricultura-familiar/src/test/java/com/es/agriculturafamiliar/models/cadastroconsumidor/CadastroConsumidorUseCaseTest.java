package com.es.agriculturafamiliar.models.cadastroconsumidor;

import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;
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
public class CadastroConsumidorUseCaseTest {
    @InjectMocks
    private CadastroConsumidorUseCase usecase;

    @Mock
    private CadastroConsumidorRepository repository;

    @Test
    public void erroAoCadastrar_consumidorJaExiste() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.of(mockCadastroConsumidor()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> usecase.cadastraConsumidor(mockCadastroConsumidor()));
    }

    @Test
    public void sucessoAoConsultar_consumidorJaExiste() {
        usecase.consultaConsumidor(Mockito.anyString());
        Mockito.verify(repository, Mockito.times(1)).consultaPorChave(Mockito.anyString());
    }

    @Test
    public void sucessoAoAtualizar_cpfExiste() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.of(mockCadastroConsumidor()));
        usecase.atualizaConsumidor(mockCadastroConsumidor());
        Mockito.verify(repository, Mockito.times(1)).salvar(mockCadastroConsumidor());
    }

    @Test
    public void erroAoAtualizar_cpfNaoExiste() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> usecase.atualizaConsumidor(mockCadastroConsumidor()));
    }

    @Test
    public void sucessoAoDeletar_cpfExiste() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.of(mockCadastroConsumidor()));
        usecase.deletaConsumidor(Mockito.anyString());
        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.anyString());
    }

    @Test
    public void erroAoDeletar_cpfNaoExiste() {
        Mockito.when(usecase.consultaConsumidor(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> usecase.deletaConsumidor(Mockito.anyString()));
    }

    private CadastroConsumidorDomain mockCadastroConsumidor() {
        return CadastroConsumidorDomain.builder()
                .cpf("11111111111")
                .build();
    }

}
