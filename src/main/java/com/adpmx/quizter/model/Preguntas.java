package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class Preguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPregunta;

    @Column
    private String pregunta;

    @Column
    private String tipoPregunta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_temas", nullable = false)
    private Temas tema;

    public Preguntas() {
    }

    public Preguntas(Long idPregunta, String pregunta, String tipoPregunta, Temas tema) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.tipoPregunta = tipoPregunta;
        this.tema = tema;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Temas getTema() {
        return tema;
    }

    public void setTema(Temas tema) {
        this.tema = tema;
    }
}
