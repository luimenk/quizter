package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class Temas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTema;

    @Column
    private String nombreTema;

    public Temas() {
    }

    public Temas(Long idTema, String nombreTema) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
}
