package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

import java.util.List;

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
    private List<Secao> listaSecoes;

}
