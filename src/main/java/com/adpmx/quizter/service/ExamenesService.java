package com.adpmx.quizter.service;

import com.adpmx.quizter.model.Examenes;
import com.adpmx.quizter.repository.ExamenesRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenesService {

    @Autowired
    private ExamenesRepository examenesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Examenes save(Examenes examenes) {
        return examenesRepository.save(examenes);
    }

    public List<Examenes> findAll() {
        return examenesRepository.findAll();
    }

    public Examenes findById(Long examenesId) {
        return examenesRepository.findByIdExamen(examenesId);
    }

    public Examenes findByNombreExamen(String nombreExamen){
        return  examenesRepository.findByNombreExamen(nombreExamen);
    }

    public void delete(Long examenesId) {
        examenesRepository.deleteById(examenesId);
    }

    public long contar() {
        return examenesRepository.count();
    }
}
