package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Ideia;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {
	//@Query("SELECT coalesce(max(ide.idIdeia), 0) FROM ideia ide")
	//Long getMaxId();
	
	@Query(value = "select * from ideia", nativeQuery = true)
    List<Ideia> findByUltimasCadastradasPageable();

	@Query(value = "select * from ideia", nativeQuery = true)
    List<Ideia> findByMaisVotadas();

    @Query(value = "select i.* from nossaideia.user u join nossaideia.favorita f on f.id_user = u.id join nossaideia.ideia i on f.id_ideia = i.id where u.username = 1?", nativeQuery = true)
    List<Ideia> findByIdeiasFavoritas (String username);

    Collection<Ideia> findBySituacao(String situacao);

}
