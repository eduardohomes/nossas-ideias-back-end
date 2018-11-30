package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.UserRepository;
import com.br.nossas.ideias.service.PasswordEncoderService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
public class AutenticarController {

    private User user;
    private final UserRepository userDAO;
    private final JSONObject jsonObject = new JSONObject();
    private final JSONArray jsonArrayToken = new JSONArray();

    public AutenticarController(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    public JSONObject autenticaUsuarioLogin(User user) {

        User usuario = userDAO.findByUsername(user.getUsername());
        int isAdmin;

        if (usuario == null) {
            throw new ResourceNotFoundExcepction("Dados invalido");
        } else {
            boolean senhaValida = PasswordEncoderService.ValidaSenha(usuario.getPassword(),  user.getPassword());
            if (!senhaValida) {
                throw new ResourceNotFoundExcepction("Dados invalido");
            } else {
                String token = PasswordEncoderService.geraToken(usuario.getPassword());
                if(usuario.isAdmin()) {
                    isAdmin = 1;
                } else {
                    isAdmin = 2;
                }

                usuario.setToken(token);
                userDAO.save(usuario);
            }
        }

        jsonObject.put("token", usuario.getToken());
        jsonObject.put("user", isAdmin);

        return jsonObject;
    }

    public User autenticaUsuarioLogado(String token) {

        user = userDAO.findByToken(token);

        return user;
    }

}
