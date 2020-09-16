package com.adpmx.quizter.service;

import com.adpmx.quizter.model.Preguntas;
import com.adpmx.quizter.model.PreguntasExamenes;
import com.adpmx.quizter.repository.PreguntasExamenesRepository;
import com.adpmx.quizter.repository.PreguntasRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntasService {

    @Autowired
    private PreguntasRepository preguntasRepository;

    @Autowired
    private PreguntasExamenesRepository preguntasExamenesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Preguntas save(Preguntas preguntas) {
        return preguntasRepository.save(preguntas);
    }

    public PreguntasExamenes saveExamenes(PreguntasExamenes preguntasExamenes){
        return preguntasExamenesRepository.save(preguntasExamenes);
    }

    public List<Preguntas> findAll() {
        return preguntasRepository.findAll();
    }

    public Preguntas findById(Long preguntasId) {
        return preguntasRepository.findByIdPregunta(preguntasId);
    }

    public Preguntas findByPregunta(String pregunta) {
        return preguntasRepository.findByPregunta(pregunta);
    }

    public List<Preguntas> findAllByIdTema(Long idTema){
        return preguntasRepository.findAllByTema_IdTema(idTema);
    }

    public List<Preguntas> findRandPreguntas(Long idTema, Long cantidad){
        return preguntasRepository.findRandPreguntas(idTema, cantidad);
    }

    public void delete(Long preguntasId) {
        preguntasRepository.deleteById(preguntasId);
    }

    public long contar() {
        return preguntasRepository.count();
    }

    public long contarAllByIdTema(Long idTema){
        return preguntasRepository.countAllByTema_IdTema(idTema);
    }
}
