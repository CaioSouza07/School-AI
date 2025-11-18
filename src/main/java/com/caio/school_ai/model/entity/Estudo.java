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

}
