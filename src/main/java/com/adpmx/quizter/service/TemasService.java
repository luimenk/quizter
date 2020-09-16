package com.adpmx.quizter.service;

import com.adpmx.quizter.model.Temas;
import com.adpmx.quizter.repository.TemasRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemasService {

    @Autowired
    private TemasRepository temasRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Temas save(Temas temas) {
        return temasRepository.save(temas);
    }

    public List<Temas> findAll() {
        return temasRepository.findAll();
    }

    public Temas findById(Long temasId) {
        return temasRepository.findByIdTema(temasId);
    }

    public void delete(Long temasId) {
        temasRepository.deleteById(temasId);
    }

    public long contar() {
        return temasRepository.count();
    }
}
