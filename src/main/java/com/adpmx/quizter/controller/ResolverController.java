package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.*;
import com.adpmx.quizter.service.ExamenesAppUserService;
import com.adpmx.quizter.service.ExamenesService;
import com.adpmx.quizter.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@CrossOrigin
public class ResolverController {
    @Autowired
    ExamenesService examenesService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ExamenesAppUserService examenesAppUserService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/resolverlos"})
    public String listar(Model model){
        List<ExamenesAppUser> examenesAppUsers = (List<ExamenesAppUser>) examenesAppUserService.findAll();
        model.addAttribute("examenesAppUsers", examenesAppUsers);
        return "resolver/index";
    }

    @RequestMapping(value = {"/resolverlos/{id}"})
    @CrossOrigin(origins = "*")
    public String agregar(@PathVariable Long id, Model model){


        return "resolver/add_meal3";
    }
}
