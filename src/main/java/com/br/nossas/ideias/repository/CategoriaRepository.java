package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "select cate.nome,\n" +
            "       (select count(*) \n" +
            "        from nossaideia.ideia ide \n" +
            "        where ide.id_categoria = cate.id\n" +
            "          and ide.situacao <> 'Aberta' \n" +
            "          and ide.situacao <> 'Rejeitada') as quantidade\n" +
            "       from nossaideia.categoria cate", nativeQuery = true)
    List<Object> listaPorCategoria();
}
