package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Ideia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {

	@Query(value = "select ide.* \n" +
            "from nossaideia.voto voto\n" +
            "right join nossaideia.ideia ide\n" +
            "on voto.id_ideia = ide.id\n" +
            "where voto.voto = 'S'\n" +
            "and ide.situacao = 'Em Execução'\n" +
            "group by voto.id_ideia\n" +
            "having count(voto.id_ideia)>0\n" +
            "ORDER BY count(voto.id_ideia) desc LIMIT 3", nativeQuery = true)
    List<Ideia> findByEmAlta();

    @Query(value = "select ide.* from nossaideia.ideia ide join nossaideia.favorita favo on ide.id = favo.id_ideia where id_user = ?1 and marcada = 'S'", nativeQuery = true)
    List<Ideia> findByFavoritas(Long idUser);

    @Query(value ="SELECT * FROM nossaideia.ideia \n" +
            "where situacao = 'Em Colaboração' \n" +
            "or situacao = 'Em Execução'\n" +
            "ORDER BY id DESC LIMIT 5" , nativeQuery = true)
    List<Ideia> findByUltimasIdeias();

    @Query(value = "select * from nossaideia.ideia where nome like '%' || ?1 || '%'",nativeQuery = true)
	List<Ideia> findByIdeia(String nome);

    @Query(value = "select * from nossaideia.ideia where situacao <> 'Rejeitada' order by id desc",nativeQuery = true)
    List<Ideia> findByPendenteAvaliacao();

    @Query(value = "select max(id) from nossaideia.ideia",nativeQuery = true)
    Long ultimaIdeiaCadastrada ();

    @Query(value = "select * from nossaideia.ideia where id_categoria = ?1 and nome like '%' || ?2 || '%'",  nativeQuery = true)
    List<Ideia> buscaPorNomeCategoria(Long idCategoria, String nome);

    List<Ideia> findByNomeContaining(String nome);

}
