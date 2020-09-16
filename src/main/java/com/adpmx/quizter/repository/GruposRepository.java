package com.adpmx.quizter.repository;

import com.adpmx.quizter.model.Grupos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GruposRepository extends JpaRepository<Grupos, Long>{
    Grupos findByIdGrupo(Long idGrupo);
    Grupos findByNombreGrupo(String nombre);
}
