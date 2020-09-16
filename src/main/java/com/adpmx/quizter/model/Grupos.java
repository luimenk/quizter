package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class Grupos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGrupo;

    @Column
    private String nombreGrupo;

    public Grupos() {
    }

    public Grupos(Long idGrupo, String nombreGrupo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
}
