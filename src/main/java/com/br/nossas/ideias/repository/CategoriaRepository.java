package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Categoria;
import com.br.nossas.ideias.model.Comentario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {

}
