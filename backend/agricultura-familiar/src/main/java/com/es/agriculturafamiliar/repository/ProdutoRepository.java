package com.es.agriculturafamiliar.repository;

import com.es.agriculturafamiliar.entity.Produto;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.Tipologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByTipologia(Integer tipologia);

    @Query(value = "SELECT * FROM produto WHERE SOUNDEX(nome) = SOUNDEX(:nome)", nativeQuery = true)
    List<Produto> findByNomeAproximado(String nome);
}
