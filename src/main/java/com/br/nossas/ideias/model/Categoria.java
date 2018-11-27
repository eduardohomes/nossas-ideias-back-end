package com.br.nossas.ideias.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria  extends  AbstractEntity{

    @NotEmpty
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
