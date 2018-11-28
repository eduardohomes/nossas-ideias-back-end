package com.br.nossas.ideias.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria  {

    @NotEmpty
    @Column(name = "nome")
    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Ideia.class, mappedBy = "categoria")
    public Set<Ideia> ideias = new HashSet<>();

    public Set<Ideia> getIdeias() {
        return ideias;
    }
    public void setIdeias(Set<Ideia> ideias) {
        this.ideias = ideias;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nome=" + nome + ", ideias=" + ideias
                + "]";
    }
}
