package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.UserRepository;
import org.springframework.stereotype.Controller;

import java.text.Format;

@Controller
public class UserController {

    private UserRepository userDAO;

    public UserController(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isAdmin(String token) {

        boolean admin= userDAO.findByToken(token).isAdmin();

        if(admin)         {
            return true;
        } else {
            return false;
        }
    }

    public String getUserName(String token) {

        User username = userDAO.findByToken(token);

        return username.getUsername();
    }

}
