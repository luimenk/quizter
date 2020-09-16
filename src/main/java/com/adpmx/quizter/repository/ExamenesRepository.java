package com.adpmx.quizter.repository;

import com.adpmx.quizter.model.Examenes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExamenesRepository extends JpaRepository<Examenes, Long>{
    Examenes findByIdExamen(Long idExamen);
    Examenes findByNombreExamen(String nombreExamen);
}
