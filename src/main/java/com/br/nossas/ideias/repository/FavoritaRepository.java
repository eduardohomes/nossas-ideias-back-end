package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Favorita;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface FavoritaRepository extends JpaRepository<Favorita, Long> {

public interface FavoritaRepository extends PagingAndSortingRepository<Favorita, Long> {

    Favorita findByIdIdeia(Long idIdeia);

    @Query(value = "select * from favorita f join user u on f.id_user = u.id where u.username = ?1", nativeQuery = true)
    List<Favorita> getFindByIdUsername(String username);
}
