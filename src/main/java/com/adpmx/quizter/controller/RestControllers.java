package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.Temas;
import com.adpmx.quizter.service.TemasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RestControllers {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private TemasService temasService;

    @RequestMapping(value = {"/getTemas"})
    public List<Temas> getTemas(){
        return temasService.findAll();
    }
}
