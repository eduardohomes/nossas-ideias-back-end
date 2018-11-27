package com.br.nossas.ideias.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Comentario  extends AbstractEntity{


    @NotEmpty
    private String comentario;

    private Long idIdeia;

    public Long getIdIdeia() {
        return idIdeia;
    }

    public void setIdIdeia(Long idIdeia) {
        this.idIdeia = idIdeia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
