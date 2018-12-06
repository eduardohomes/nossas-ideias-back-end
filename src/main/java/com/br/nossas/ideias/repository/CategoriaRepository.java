package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "select cate.*, count(*) from nossaideia.ideia ide " +
            "join nossaideia.categoria cate " +
            "on ide.id_categoria = cate.id " +
            "where ide.situacao <> 'Aberta' " +
            "and ide.situacao <> 'Rejeitada' " +
            "group by ide.id_categoria", nativeQuery = true)
    List<Object> listaPorCategoria();
}
