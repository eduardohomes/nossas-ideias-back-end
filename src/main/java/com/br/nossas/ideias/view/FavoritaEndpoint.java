package com.br.nossas.ideias.view;

import com.br.nossas.ideias.controller.AutenticarController;
import com.br.nossas.ideias.controller.FavoritaController;
import com.br.nossas.ideias.controller.IdeiaController;
import com.br.nossas.ideias.model.Favorita;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/favoritas")
public class FavoritaEndpoint {

    private final AutenticarController autenticarController;
    private final FavoritaController favoritaController;
    private final IdeiaController ideiaController;

    public FavoritaEndpoint(AutenticarController autenticarController, FavoritaController favoritaController, IdeiaController ideiaController) {
        this.autenticarController = autenticarController;
        this.favoritaController = favoritaController;
        this.ideiaController = ideiaController;
    }

    /*@GetMapping
    @CrossOrigin
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(favoritaDAO.findAll(),HttpStatus.OK);
    }*/

    @PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Favorita favorita, @RequestHeader("Authorization") String token) {

        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (idUserLogado != null) {
            favoritaController.salvaFavorita(favorita, idUserLogado);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}