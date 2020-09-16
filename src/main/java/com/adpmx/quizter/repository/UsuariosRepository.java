package com.adpmx.quizter.repository;

import com.adpmx.quizter.model.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
    Usuarios findByUsername(String username);
    Usuarios findByUserId(Long id);
}
