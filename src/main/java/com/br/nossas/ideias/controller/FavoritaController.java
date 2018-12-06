package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.repository.FavoritaRepository;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;

@Controller
public class FavoritaController {

    private final FavoritaRepository favoritaRepository;
    private final UserController userController;
    private JSONArray jsonArrayVotos;

    public FavoritaController(FavoritaRepository favoritaRepository, UserController userController) {
        this.favoritaRepository = favoritaRepository;
        this.userController = userController;
        jsonArrayVotos = new JSONArray();
    }

    public void salvaFavorita(Favorita favorita, Long idUsuario) {

        favorita.setIdUser(idUsuario);
        int tamanho = favoritaRepository.findByIdIdeiaAndIdUser(favorita.getIdIdeia(),idUsuario).size();

        if(tamanho > 0) {
            String marcada = favoritaRepository.findByIdIdeiaAndIdUser(favorita.getIdIdeia(),idUsuario).get(0).getMarcada();
            long idFavorita = favoritaRepository.findByIdIdeiaAndIdUser(favorita.getIdIdeia(), idUsuario).get(0).getId();
            favorita.setId(idFavorita);
            if(marcada.equals("N")) {
                favorita.setMarcada("S");
                favoritaRepository.save(favorita);
            } else {
                favorita.setMarcada("N");
                favoritaRepository.save(favorita);
            }
        } else {
            favorita.setMarcada("S");
            favoritaRepository.save(favorita);
        }
    }
}
