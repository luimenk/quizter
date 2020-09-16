package com.adpmx.quizter.repository;

import com.adpmx.quizter.model.Preguntas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PreguntasRepository extends JpaRepository<Preguntas, Long>{
    Preguntas findByIdPregunta(Long idPregunta);
    Preguntas findByPregunta(String pregunta);
    List<Preguntas> findAllByTema_IdTema(Long idTema);
    Long countAllByTema_IdTema(Long idTema);

    //SELECT * FROM preguntas WHERE id_temas = 60 ORDER BY RAND() LIMIT 2;
    @Query(value = "select * from preguntas where id_temas = :idTema ORDER BY RAND() LIMIT :cantidad", nativeQuery = true)
    List<Preguntas> findRandPreguntas(@Param("idTema") Long idTema, @Param("cantidad") Long cantidad);
}
