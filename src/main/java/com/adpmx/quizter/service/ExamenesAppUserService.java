package com.adpmx.quizter.service;

import com.adpmx.quizter.model.ExamenesAppUser;
import com.adpmx.quizter.repository.ExamenesAppUserRepository;

import com.adpmx.quizter.repository.ExamenesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenesAppUserService{

    @Autowired
    private ExamenesAppUserRepository examenesAppUserRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public ExamenesAppUser save(ExamenesAppUser examenesAppUser) {
        return examenesAppUserRepository.save(examenesAppUser);
    }

    public List<ExamenesAppUser> findAll() {
        return examenesAppUserRepository.findAll();
    }

    /*public Examenes findById(Long examenesId) {
        return examenesRepository.findByIdExamen(examenesId);
    }

    public Examenes findByNombreExamen(String nombreExamen){
        return  examenesRepository.findByNombreExamen(nombreExamen);
    }*/

    public void delete(Long examenesId) {
        examenesAppUserRepository.deleteById(examenesId);
    }

    public long contar() {
        return examenesAppUserRepository.count();
    }
}
