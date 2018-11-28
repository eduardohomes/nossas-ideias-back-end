package com.br.nossas.ideias.model;

import javax.persistence.*;

@Entity
public class Ideia {

    private String nome;
    private String descricao;
    private String comentarioAvaliador;
    private String ativa;
    private String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComentarioAvaliador() {
        return comentarioAvaliador;
    }

    public void setComentarioAvaliador(String comentarioAvaliador) {
        this.comentarioAvaliador = comentarioAvaliador;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Ideia [id=" + id + ", nome=" + nome + "]";
    }
}
