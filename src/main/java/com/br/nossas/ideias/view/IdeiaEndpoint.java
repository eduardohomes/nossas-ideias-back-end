package com.br.nossas.ideias.view;


import com.br.nossas.ideias.controller.AutenticarController;
import com.br.nossas.ideias.controller.IdeiaController;
import com.br.nossas.ideias.controller.UserController;
import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Ideia;
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
    private JSONObject jsonObjectList = new JSONObject();

	public IdeiaEndpoint(IdeiaController ideiaController, UserController userController, AutenticarController autenticarController, Optional<Ideia> ideiaListOptional) {
        this.ideiaController = ideiaController;
        this.userController = userController;
        this.autenticarController = autenticarController;
        this.ideiaListOptional = ideiaListOptional;
    }

    @GetMapping(value = "/listaTodasIdeias")
    @CrossOrigin
    public ResponseEntity<JSONObject> listaTodasIdeias(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {

            String username = userController.getUserName(token);

            if (userController.isAdmin(token)) {
                jsonObjectList.put("isAdmin", "S");
            } else {
                jsonObjectList.put("isAdmin", "N");
            }

            //jsonObjectList.put("listarFavoritas", ideiaController.listarFavoritas());
            //jsonObjectList.put("listaUltimasIdeias", ideiaController.listaUltimasIdeias());
            jsonObjectList.put("listaEmAlta", ideiaController.listaEmAlta());
            return new ResponseEntity<>(jsonObjectList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping(value = "/listaUltimasIdeias")
    @CrossOrigin
    public ResponseEntity<JSONObject> listaUltimasIdeias(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {

            String username = userController.getUserName(token);

            if (userController.isAdmin(token)) {
                jsonObjectList.put("isAdmin", "S");
            } else {
                jsonObjectList.put("isAdmin", "N");
            }

            jsonObjectList.put("listaUltimasIdeias", ideiaController.listaUltimasIdeias());
            return new ResponseEntity<>(jsonObjectList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping(value = "/listaFavoritas")
    @CrossOrigin
    public ResponseEntity<JSONObject> listaFavoritas(@RequestHeader("Authorization") String token) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {

            String username = userController.getUserName(token);

            if (userController.isAdmin(token)) {
                jsonObjectList.put("isAdmin", "S");
            } else {
                jsonObjectList.put("isAdmin", "N");
            }

            jsonObjectList.put("listarFavoritas", ideiaController.listarFavoritas());
            return new ResponseEntity<>(jsonObjectList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getIdeiaById(@RequestHeader("token") String token, @PathVariable("id") Long id) {

        if (autenticarController.autenticaUsuarioLogado(token) != null) {
            ideiaListOptional = ideiaController.listaPorId(id);
        }
        return new ResponseEntity<>(ideiaListOptional,HttpStatus.OK);
    }

    /*@PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Ideia ideia, @RequestHeader("Token") String token) {

	    Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();
	    boolean isAdmin = autenticarController.autenticaUsuarioLogado(token).isAdmin();

        if (idUserLogado != null) {
            ideiaController.salvaIdeia(ideia, idUserLogado, isAdmin);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }  else {
            throw new ResourceNotFoundExcepction("Usuario não encontrado");
        }
    }*/

    @PutMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@Valid @RequestBody Ideia ideia, @RequestHeader("Token") String token) {

        Long idUserLogado = autenticarController.autenticaUsuarioLogado(token).getId();
        boolean isAdmin = autenticarController.autenticaUsuarioLogado(token).isAdmin();

        if(isAdmin) {
            ideiaController.atualizarIdeia(ideia,idUserLogado);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            throw new ResourceNotFoundExcepction("Voce nao tem permissao para executar essa operacao");
        }
    }
}
