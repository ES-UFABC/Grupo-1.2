package com.es.agriculturafamiliar.repository;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    List<Produtor> findByEnderecosEstadoAndEnderecosMunicipio(String estado, String cidade);
}
