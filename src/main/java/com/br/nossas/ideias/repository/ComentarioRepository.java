package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByIdIdeia(Long idIdeia);
}
