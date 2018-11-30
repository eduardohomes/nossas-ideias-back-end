package com.br.nossas.ideias.view;

import com.br.nossas.ideias.controller.AutenticarController;
import com.br.nossas.ideias.controller.UserController;
import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

 @RestController
@RequestMapping("api/user")
public class LoginEndpoint {

    private final UserRepository userDAO;
    private final AutenticarController autenticarController;

    public LoginEndpoint(UserRepository userDAO, UserController userController, AutenticarController autenticarController) {
        this.userDAO = userDAO;
        this.autenticarController = autenticarController;
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> findByUsername(@Valid @RequestBody User user) {
        return new ResponseEntity<>(autenticarController.autenticaUsuarioLogin(user), HttpStatus.OK);
    }
}