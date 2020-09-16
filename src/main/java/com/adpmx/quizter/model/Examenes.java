package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class Examenes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idExamen;

    @Column
    private String nombreExamen;

    public Examenes() {
    }

    public Examenes(Long idExamen, String nombreExamen) {
        this.idExamen = idExamen;
        this.nombreExamen = nombreExamen;
    }

    public Long getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Long idExamen) {
        this.idExamen = idExamen;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }
}
