package com.es.agriculturafamiliar.endereco.service;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.enums.TipoEndereco;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.services.EnderecoService;
import com.es.agriculturafamiliar.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    void saveEndereco_shouldReturnSavedEndereco_whenSuccessful() {
        Endereco endereco = new Endereco(null, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);

        Endereco returnedSavedEndereco = new Endereco(1L, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);

        when(enderecoRepository.save(any(Endereco.class)))
                .thenReturn(returnedSavedEndereco);

        Endereco savedEndereco = enderecoService.saveEndereco(endereco);

        assertEquals(endereco.getTipoEndereco(), savedEndereco.getTipoEndereco());
        assertEquals(endereco.getMunicipio(), savedEndereco.getMunicipio());
        assertNotNull(savedEndereco.getId());
    }

    @Test
    void findByProdutorId_shouldThrowNotFoundException_whenResourceIsNotFound() {
        when(enderecoRepository.findByProdutorId(any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> enderecoService.findByProdutorId(33l));
    }


    @Test
    void findById_shouldThrowNotFoundException_whenResourceIsNotFound(){
        when(enderecoRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> enderecoService.findById(33l));
    }

    @Test
    void findById_shouldReturnSavedEndereco_whenSuccessful() {
        Endereco endereco = new Endereco(1L, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);

        when(enderecoRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(endereco));

        Endereco returnedEndereco = enderecoService.findById(1l);

        assertEquals(endereco.getTipoEndereco(), returnedEndereco.getTipoEndereco());
        assertEquals(endereco.getMunicipio(), returnedEndereco.getMunicipio());
        assertEquals(endereco.getRua(), returnedEndereco.getRua());
        assertEquals(endereco.getId(), returnedEndereco.getId());
    }

    @Test
    void deleteEnderecoById_shouldThrowResourceNotFoundException_whenResourceIsNotFound() {
        when(enderecoRepository.findById(any(Long.class)))
                .thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> enderecoService.deleteEnderecoById(33l));
    }

}