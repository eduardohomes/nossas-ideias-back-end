package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(value = "select * from nossaideia.voto where id_usuario = 1? and voto='S'", nativeQuery = true)
    List<Voto> listaVotos(Long idUser);

    @Query(value = "select vo.* from nossaideia.voto vo where vo.id_user = ?1 and vo.id_ideia = ?2", nativeQuery = true)
    List<String> validaVoto(Long idUser, Long idIdeia);

    @Query(value = "select count(*) from nossaideia.voto where id_ideia = ?1 and voto = ?2", nativeQuery = true)
    long countByIdIdeiaAndVoto(Long idIdeia, String voto);

    long countByIdIdeia(Long idIdeia);

    @Query(value = "select vt.id_ideia,vt.voto, count(*) as quantidade from nossaideia.voto vt group by id_ideia, voto", nativeQuery = true)
    List<Voto> contaVoto();
}