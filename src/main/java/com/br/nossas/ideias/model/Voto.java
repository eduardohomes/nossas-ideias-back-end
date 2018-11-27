package com.br.nossas.ideias.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Voto extends AbstractEntity{

    @NotEmpty
    private String voto;

    private Long idIdeia;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    private Long idUser;

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public Long getIdIdeia() {
        return idIdeia;
    }

    public void setIdIdeia(Long idIdeia) {
        this.idIdeia = idIdeia;
    }

}
