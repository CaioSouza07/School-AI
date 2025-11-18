package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

@Entity
public class Estudo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String resumo;
    private String mapaMental;
    private String questoes;
    private String sugestoes;

    @ManyToOne
    @JoinColumn(name = "secao_id", nullable = false)
    private Secao secao;

    public Estudo() {
    }

    public Estudo(Long id, String titulo, String resumo, String mapaMental, String questoes, String sugestoes, Secao secao) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.mapaMental = mapaMental;
        this.questoes = questoes;
        this.sugestoes = sugestoes;
        this.secao = secao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getMapaMental() {
        return mapaMental;
    }

    public void setMapaMental(String mapaMental) {
        this.mapaMental = mapaMental;
    }

    public String getQuestoes() {
        return questoes;
    }

    public void setQuestoes(String questoes) {
        this.questoes = questoes;
    }

    public String getSugestoes() {
        return sugestoes;
    }

    public void setSugestoes(String sugestoes) {
        this.sugestoes = sugestoes;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
}
