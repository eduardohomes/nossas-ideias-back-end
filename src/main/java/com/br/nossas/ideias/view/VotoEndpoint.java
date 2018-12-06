package com.br.nossas.ideias.view;

import com.br.nossas.ideias.controller.AutenticarController;
import com.br.nossas.ideias.controller.VotoController;
import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Voto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/votos")
public class VotoEndpoint {

    private final VotoController votoController;
    private final AutenticarController autenticarController;


    public VotoEndpoint(VotoController votoController, AutenticarController autenticarController) {
        this.votoController = votoController;
        this.autenticarController = autenticarController;
    }

    @GetMapping(value = "/todos")
    @CrossOrigin
    public ResponseEntity<?> listAll(@RequestHeader("Authorization") String token) {
        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (idUserLogado != null) {
            votoController.listaVotos(idUserLogado);
            return new ResponseEntity<>(HttpStatus.OK);
        }  else {
            throw new ResourceNotFoundExcepction("Usuario não encontrado");
        }
    }

    @PutMapping
    @CrossOrigin
    public ResponseEntity<?> listaTodosPorIdeia(@Valid @RequestBody Voto voto, @RequestHeader("Authorization") String token) {
        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (idUserLogado != null) {
            return new ResponseEntity<>(votoController.listaVotosPorIdeia(voto.getIdIdeia()),HttpStatus.OK);
        }  else {
            throw new ResourceNotFoundExcepction("Usuario não encontrado");
        }
    }


    @PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Voto voto, @RequestHeader("Authorization") String token) {

        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (idUserLogado != null) {
            return new ResponseEntity<>(votoController.validaVoto(idUserLogado, voto), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundExcepction("Usuario nao encontrado");
        }
    }

    @GetMapping(value = "/contaVotos")
    @CrossOrigin
    public ResponseEntity<?> contaVoto(@RequestHeader("Authorization") String token) {
        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(votoController.contaVoto(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}