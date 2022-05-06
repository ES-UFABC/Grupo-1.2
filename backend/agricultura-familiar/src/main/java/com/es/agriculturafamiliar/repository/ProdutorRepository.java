package com.es.agriculturafamiliar.repository;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    Optional<Produtor> findProdutorByUserId(Long id);
    List<Produtor> findByEnderecosEstadoAndEnderecosMunicipio(String estado, String cidade);

    @Query(value = "SELECT * FROM produtor WHERE SOUNDEX(nome_fantasia) = SOUNDEX(:nomeFantasia)", nativeQuery = true)
    List<Produtor> findByNomeFantasiaAproximado(String nomeFantasia);
}
