package com.adpmx.quizter.service;

import com.adpmx.quizter.model.Grupos;
import com.adpmx.quizter.repository.GruposRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GruposService {

    @Autowired
    private GruposRepository gruposRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Grupos save(Grupos grupos) {
        return gruposRepository.save(grupos);
    }

    public List<Grupos> findAll() {
        return gruposRepository.findAll();
    }

    public Grupos findById(Long gruposId) {
        return gruposRepository.findByIdGrupo(gruposId);
    }

    public Grupos findByNombre(String nombre){
        return gruposRepository.findByNombreGrupo(nombre);
    }

    public void delete(Long gruposId) {
        gruposRepository.deleteById(gruposId);
    }

    public long contar() {
        return gruposRepository.count();
    }
}
