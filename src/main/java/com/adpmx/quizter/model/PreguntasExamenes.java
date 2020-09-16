package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class PreguntasExamenes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_examen", nullable = false)
    private Examenes examen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pregunta", nullable = false)
    private Preguntas pregunta;

    public PreguntasExamenes() {
    }

    public PreguntasExamenes(Long id, Examenes examen, Preguntas pregunta) {
        this.id = id;
        this.examen = examen;
        this.pregunta = pregunta;
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

    public Preguntas getPregunta() {
        return pregunta;
    }

    public void setPregunta(Preguntas pregunta) {
        this.pregunta = pregunta;
    }
}
