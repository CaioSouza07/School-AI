package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cor;

    @ManyToOne
    @JoinColumn(name = "agrupamento_id", nullable = false)
    private Agrupamento agrupamento;

    @OneToMany(mappedBy = "secao")
    private List<Estudo> listaEstudos;


    public Secao() {
    }

    public Secao(Long id, String nome, String cor, Agrupamento agrupamento, List<Estudo> listaEstudos) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.agrupamento = agrupamento;
        this.listaEstudos = listaEstudos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Agrupamento getAgrupamento() {
        return agrupamento;
    }

    public void setAgrupamento(Agrupamento agrupamento) {
        this.agrupamento = agrupamento;
    }

    public List<Estudo> getListaEstudos() {
        return listaEstudos;
    }

    public void setListaEstudos(List<Estudo> listaEstudos) {
        this.listaEstudos = listaEstudos;
    }
}
