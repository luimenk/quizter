package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class ExamenesAppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_examen", nullable = false)
    private Examenes examen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuarios usuarios;

    public ExamenesAppUser() {
    }

    public ExamenesAppUser(Long id, Examenes examen, Usuarios usuarios) {
        this.id = id;
        this.examen = examen;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Examenes getExamen() {
        return examen;
    }

    public void setExamen(Examenes examen) {
        this.examen = examen;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
}
