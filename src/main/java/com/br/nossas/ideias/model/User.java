package com.br.nossas.ideias.model;

import javax.persistence.Entity;


@Entity
public class User extends AbstractEntity {

    private String username;
    private String password;
    private String nome;
    private boolean admin;
    private String token;
    private Long facebook;

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Long getFacebook() {
        return facebook;
    }

    public void setFacebook(Long facebook) {
        this.facebook = facebook;
    }
}