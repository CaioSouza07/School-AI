package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Secao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cor;

    @ManyToOne
    @JoinColumn(name = "agrupamento_id", nullable = false)
    private Agrupamento agrupamento;

    @OneToMany(mappedBy = "secao")
    private List<Estudo> listaEstudos;

}
