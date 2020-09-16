package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.*;
import com.adpmx.quizter.service.ExamenesAppUserService;
import com.adpmx.quizter.service.ExamenesService;
import com.adpmx.quizter.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@CrossOrigin
public class AsignacionController {

    @Autowired
    ExamenesService examenesService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ExamenesAppUserService examenesAppUserService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/asignacions"})
    public String listar(Model model){
        List<ExamenesAppUser> examenesAppUsers = (List<ExamenesAppUser>) examenesAppUserService.findAll();
        model.addAttribute("examenesAppUsers", examenesAppUsers);
        return "asignacions/index";
    }

    @RequestMapping(value = {"/agregarAsignacions"})
    public String agregar(Model model){
        List<Usuarios> usuarios = (List<Usuarios>) userDetailsService.findAll();
        List<Examenes> examenes = (List<Examenes>) examenesService.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("examenes", examenes);

        return "asignacions/datos";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registrarAsignacion", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public String registrar(@RequestParam Long id,
                            @RequestParam Long userId,
                            @RequestParam Long idExamen) {
        Usuarios usuario = userDetailsService.findByUserId(userId);
        Examenes examen = examenesService.findById(idExamen);
        ExamenesAppUser examenesAppUser = new ExamenesAppUser();

        examenesAppUser.setUsuarios(usuario);
        examenesAppUser.setExamen(examen);

        examenesAppUserService.save(examenesAppUser);

        return "redirect:/asignacions";
    }

    @RequestMapping("/eliminarAsignacion/{id}")
    @CrossOrigin(origins = "*")
    public String delete(@PathVariable Long id) {
        try {
            examenesAppUserService.delete(id);
        } catch (Exception ex){
            return "redirect:/asignacions?error=true";
        }
        return "redirect:/asignacions";
    }
}
