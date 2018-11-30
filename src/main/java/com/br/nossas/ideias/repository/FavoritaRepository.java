package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Favorita;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoritaRepository extends JpaRepository<Favorita,Long> {


}
