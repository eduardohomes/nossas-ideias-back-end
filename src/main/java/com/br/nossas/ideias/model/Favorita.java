package com.br.nossas.ideias.model;

import javax.persistence.Entity;

@Entity
public class Favorita extends AbstractEntity{

    private Long idIdeia;
    private Long idUser;
    private String marcada;

    public String getMarcada() {
        return marcada;
    }

    public void setMarcada(String marcada) {
        this.marcada = marcada;
    }

    public Long getIdIdeia() {
        return idIdeia;
    }

    public void setIdIdeia(Long idIdeia) {
        this.idIdeia = idIdeia;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
