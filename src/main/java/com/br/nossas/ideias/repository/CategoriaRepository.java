package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Categoria;
import com.br.nossas.ideias.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
