package com.caio.school_ai.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

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
    private Set<Agrupamento> listaAgrupamentos;

    public Pasta() {
    }

    public Pasta(Long id, String nome, Usuario usuario, Set<Agrupamento> listaAgrupamentos) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.listaAgrupamentos = listaAgrupamentos;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Agrupamento> getListaAgrupamentos() {
        return listaAgrupamentos;
    }

    public void setListaAgrupamentos(Set<Agrupamento> listaAgrupamentos) {
        this.listaAgrupamentos = listaAgrupamentos;
    }
}
