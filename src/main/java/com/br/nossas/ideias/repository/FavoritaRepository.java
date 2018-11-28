package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Favorita;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface FavoritaRepository extends JpaRepository<Favorita, Long> {

    Favorita findByIdIdeia(Long idIdeia);

    @Query(value = "select f.* from nossaideia.favorita f join nossaideia.ideia i on f.id_ideia = i.id join nossaideia.user u on f.id_user = u.id", nativeQuery = true)
    List<Favorita> getFindByUsername(String username);
}
