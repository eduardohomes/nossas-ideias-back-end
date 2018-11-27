package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findByIdIdeiaAndVoto(Long idIdeia, String voto);
}