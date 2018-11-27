package com.br.nossas.ideias.model;

import javax.persistence.Entity;

@Entity

public class Dashboard extends AbstractEntity{

    private Long idIdeia;
    private String nome;
    private String descricao;
    private String comentarioAvaliador;
    private String ativa;
    private String situacao;
    private String marcada;

    public Long getIdIdeia() {
        return idIdeia;
    }

    public void setIdIdeia(Long idIdeia) {
        this.idIdeia = idIdeia;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMarcada() {
        return marcada;
    }

    public void setMarcada(String marcada) {
        this.marcada = marcada;
    }
}
