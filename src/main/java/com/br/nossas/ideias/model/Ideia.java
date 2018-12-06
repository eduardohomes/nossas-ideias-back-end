package com.br.nossas.ideias.model;

import javax.persistence.*;

@Entity
public class Ideia  extends AbstractEntity{

    private String nome;
    private String descricao;
    private String comentario_Avaliador;
    private String ativa;
    private String situacao;
    private Long idCategoria;

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

    public String getComentario_Avaliador() {
        return comentario_Avaliador;
    }

    public void setComentario_Avaliador(String comentario_Avaliador) {
        this.comentario_Avaliador = comentario_Avaliador;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

}
