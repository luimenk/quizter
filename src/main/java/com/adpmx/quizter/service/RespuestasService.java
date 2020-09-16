package com.adpmx.quizter.service;

import com.adpmx.quizter.model.Preguntas;
import com.adpmx.quizter.model.Respuestas;
import com.adpmx.quizter.repository.RespuestasRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestasService {
    
    @Autowired
    private RespuestasRepository respuestasRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Respuestas save(Respuestas respuestas) {
        return respuestasRepository.save(respuestas);
    }

    public List<Respuestas> findAll() {
        return respuestasRepository.findAll();
    }

    public Respuestas findById(Long respuestasId) {
        return respuestasRepository.findByIdRespuesta(respuestasId);
    }

    public void delete(Long respuestasId) {
        respuestasRepository.deleteById(respuestasId);
    }

    public List<Respuestas> findAllByIdPregunta(Long id){
        return respuestasRepository.findAllByPregunta_IdPregunta(id);
    }

    public long contar() {
        return respuestasRepository.count();
    }
}
