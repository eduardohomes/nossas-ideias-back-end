package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Favorita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FavoritaRepository extends JpaRepository<Favorita,Long> {
    List<Favorita> findByIdIdeiaAndIdUser(Long idIdeia, Long idUser);
}
