package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.UserRepository;
import com.br.nossas.ideias.service.PasswordEncoderService;
import org.springframework.stereotype.Controller;

@Controller
public class AutenticarController {

    private User user;
    private final UserRepository userDAO;
    private User retorno = new User();

    public AutenticarController(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    public String autenticaUsuarioLogin(User user) {

        User usuario = userDAO.findByUsername(user.getUsername());

        if (usuario == null) {
            throw new ResourceNotFoundExcepction("Dados invalido");
        } else {
            boolean senhaValida = PasswordEncoderService.ValidaSenha(usuario.getPassword(),  user.getPassword());
            if (senhaValida) {
                throw new ResourceNotFoundExcepction("Dados invalido");
            } else {
                String token = PasswordEncoderService.geraToken(usuario.getPassword());

                usuario.setToken(token);
                userDAO.save(usuario);


                retorno.setToken(token);
            }
        } 
        return retorno.getToken();
    }

    public User autenticaUsuarioLogado(String token) {

        user = userDAO.findByPassword(token);

        return user;
    }

}
