package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.model.Ideia;
import com.br.nossas.ideias.repository.IdeiaRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
public class IdeiaController {

    private List<Ideia> ideiaList = new ArrayList<>();
    private List<Ideia> ideiaListUltimas = new ArrayList<>();
    private  List<Ideia> ideiaListMaisVotadas =  new ArrayList<>();
    private  List<Ideia> ideiaListTodas =  new ArrayList<>();
    private  List<Ideia> ideiaListTeste =  new ArrayList<>();
    private final IdeiaRepository ideiaDAO;

    public IdeiaController(IdeiaRepository ideiaDAO) {
        this.ideiaDAO = ideiaDAO;
    }

    public List<Ideia> listaTodasdeias () {

        ideiaDAO.findAll().forEach(ideiaList::add);

        return ideiaList;
    }

    public List<Ideia> listUltimasIdeiasCadastradas () {

        ideiaDAO.findAll().forEach(ideiaListUltimas::add);

        return ideiaListUltimas;
    }

    public List<Ideia> listaIdeiasMaisVotadas () {

        ideiaDAO.findByMaisVotadas().forEach(ideiaListMaisVotadas::add);

        return ideiaListMaisVotadas;
    }

    public List<Ideia> listaIdeiasFavoritas (String username) {

        ideiaDAO.findByIdeiasFavoritas(username).forEach(ideiaListTodas::add);

        return  ideiaListTodas;
    }

    public List<Ideia> listaTeste(String situacao) {

        ideiaDAO.findByIdeiasFavoritas("username").stream().forEach(ideiaList::add);

        return ideiaList;
    }

}
