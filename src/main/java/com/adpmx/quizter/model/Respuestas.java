package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class Respuestas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRespuesta;

    @Column
    private String respuesta;

    @Column
    private String valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_preguntas", nullable = false)
    private Preguntas pregunta;

    public Respuestas() {
    }

    public Respuestas(Long idRespuesta, String respuesta, String valor, Preguntas pregunta) {
        this.idRespuesta = idRespuesta;
        this.respuesta = respuesta;
        this.valor = valor;
        this.pregunta = pregunta;
    }

    public Long getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Long idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Preguntas getPregunta() {
        return pregunta;
    }

    public void setPregunta(Preguntas pregunta) {
        this.pregunta = pregunta;
    }
}
