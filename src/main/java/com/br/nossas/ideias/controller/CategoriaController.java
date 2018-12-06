package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;

@Controller
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
}
