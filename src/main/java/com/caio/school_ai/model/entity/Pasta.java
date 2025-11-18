package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="pastas")
public class Pasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pasta")
    private List<Agrupamento> listaAgrupamentos;




}
