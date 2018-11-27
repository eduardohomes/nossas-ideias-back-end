package com.br.nossas.ideias.endpoint;

import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.UserRepository;
import com.br.nossas.ideias.service.PasswordEncoderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class LoginEndpoint {

    private final UserRepository userDAO;

    public LoginEndpoint(UserRepository userDAO) {
        this.userDAO = userDAO;
    }


    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> findByUsername(@Valid @RequestBody User user) {

        User usuario = userDAO.findByUsername(user.getUsername());

        /*if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean senhaValida = PasswordEncoderService.ValidaSenha(usuario.getPassword(),  user.getPassword());

        if (!senhaValida) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

        String token = PasswordEncoderService.geraToken(usuario.getPassword());

        User retorno = new User();

        retorno.setToken(token);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
}