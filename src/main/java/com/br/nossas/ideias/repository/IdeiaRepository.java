package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Ideia;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {

	@Query(value = "SELECT * FROM nossaideia.ideia where situacao <> " +
            "'Em Colaboração' ORDER BY id DESC LIMIT 3", nativeQuery = true)
    List<Ideia> findByUltimasIdeias();

	@Query(value = "select ide.* \n" +
            "from nossaideia.voto voto\n" +
            "right join nossaideia.ideia ide\n" +
            "on voto.id_ideia = ide.id\n" +
            "where voto.voto = 'S'\n" +
            "group by voto.id_ideia \n" +
            "having count(voto.id_ideia)>0\n" +
            "ORDER BY count(voto.id_ideia) desc LIMIT 5", nativeQuery = true)
    List<Ideia> findByEmAlta();

    @Query(value = "select * from nossaideia.ideia where nome like '%' || ?1 || '%'",nativeQuery = true)
	List<Ideia> findByIdeia(String nome);

    @Query(value = "select max(id) from nossaideia.ideia",nativeQuery = true)
    Long ultimaIdeiaCadastrada ();
}
