package com.adpmx.quizter.repository;

import com.adpmx.quizter.model.Preguntas;
import com.adpmx.quizter.model.Respuestas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RespuestasRepository extends JpaRepository<Respuestas, Long>{
    Respuestas findByIdRespuesta(Long idRespuesta);
    List<Respuestas> findAllByPregunta_IdPregunta(Long idPregunta);
}
