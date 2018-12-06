package com.br.nossas.ideias.view;

import com.br.nossas.ideias.controller.AutenticarController;
import com.br.nossas.ideias.controller.IdeiaController;
import com.br.nossas.ideias.controller.UserController;
import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Ideia;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/ideias")
public class IdeiaEndpoint {

    private final IdeiaController ideiaController;
    private final UserController userController;
    private final AutenticarController autenticarController;
    private Optional<Ideia> ideiaListOptional;

	public IdeiaEndpoint(IdeiaController ideiaController, UserController userController, AutenticarController autenticarController, Optional<Ideia> ideiaListOptional) {
        this.ideiaController = ideiaController;
        this.userController = userController;
        this.autenticarController = autenticarController;
        this.ideiaListOptional = ideiaListOptional;
    }

    @GetMapping(value = "/listaEmAlta")
    @CrossOrigin
    public ResponseEntity<JSONArray> listaEmAlta(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(ideiaController.listaEmAlta(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/listaPendenteAvaliacao")
    @CrossOrigin
    public ResponseEntity<JSONArray> listaPendenteAvaliacao(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(ideiaController.listaPendenteAvaliacao(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/listaUltimasIdeias")
    @CrossOrigin
    public ResponseEntity<JSONArray> listaUltimasIdeias(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(ideiaController.listaUltimasIdeias(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/listaFavoritas")
    @CrossOrigin
    public ResponseEntity<JSONArray> listaFavoritas(@RequestHeader("Authorization") String token) {

        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (idUserLogado != null) {
            return new ResponseEntity<>(ideiaController.listarFavoritas(idUserLogado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/listaPorCategoria")
    @CrossOrigin
    public ResponseEntity<?> listaPorCategoria(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(ideiaController.listaPorCategoria(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/buscaPorCategoria")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> buscaPorCategoria(@RequestHeader("Authorization") String token, @Valid @RequestBody Ideia ideia) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(ideiaController.listaBuscaAssunto(ideia.getNome(), ideia.getIdCategoria()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getIdeiaById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            ideiaListOptional = ideiaController.listaPorId(id);
        }
        return new ResponseEntity<>(ideiaListOptional,HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Ideia ideia, @RequestHeader("Authorization") String token) {

	    Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (idUserLogado != null) {
            ideiaController.salvaIdeia(ideia, idUserLogado);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }  else {
            throw new ResourceNotFoundExcepction("Usuario não encontrado");
        }
    }

    @PutMapping(value = "/atualizar")
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@Valid @RequestBody Ideia ideia, @RequestHeader("Authorization") String token) {

        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();
        boolean isAdmin = autenticarController.autenticaUsuarioLogado(token).isAdmin();

        if(isAdmin) {
                ideiaController.atualizarIdeia(ideia);
                return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            throw new ResourceNotFoundExcepction("Voce nao tem permissao para executar essa operacao");
        }
    }

    @PutMapping(value = "/buscaPorNome")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> buscaPorNome(@RequestHeader("Authorization") String token, @Valid @RequestBody Ideia ideia) {

        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            return new ResponseEntity<>(ideiaController.buscaPorNome(ideia.getNome()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
