package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Ideia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {
	//@Query("SELECT coalesce(max(ide.idIdeia), 0) FROM ideia ide")
	//Long getMaxId();
	
	@Query(value = "SELECT * FROM ideia WHERE situacao = 'Pendente'", nativeQuery = true)
    List<Ideia> findBySituacao();
	
	@Query(value = "SELECT * FROM ideia WHERE situacao = 'Pendente'", nativeQuery = true)
    List<Ideia> findByUltimasCadastradas();
	
	@Query(value = "SELECT * FROM ideia WHERE situacao = 'Pendente'", nativeQuery = true)
    List<Ideia> findByMaisVotadas();

}
