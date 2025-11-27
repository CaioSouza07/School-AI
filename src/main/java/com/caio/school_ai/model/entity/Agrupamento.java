package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Agrupamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name="pasta_id", nullable = false)
    private Pasta pasta;

    @OneToMany(mappedBy = "agrupamento")
    private Set<Secao> listaSecoes;

    public Agrupamento() {
    }

    public Agrupamento(Long id, String nome, Pasta pasta, Set<Secao> listaSecoes) {
        this.id = id;
        this.nome = nome;
        this.pasta = pasta;
        this.listaSecoes = listaSecoes;
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

    public Pasta getPasta() {
        return pasta;
    }

    public void setPasta(Pasta pasta) {
        this.pasta = pasta;
    }

    public Set<Secao> getListaSecoes() {
        return listaSecoes;
    }

    public void setListaSecoes(Set<Secao> listaSecoes) {
        this.listaSecoes = listaSecoes;
    }
}
