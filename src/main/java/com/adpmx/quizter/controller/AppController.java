package com.adpmx.quizter.controller;

import com.adpmx.quizter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class AppController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private GruposService gruposService;

    @Autowired
    private ExamenesService examenesService;

    @Autowired
    private TemasService temasService;

    @Autowired
    private PreguntasService preguntasService;

    @Autowired
    private ExamenesAppUserService examenesAppUserService;

    @RequestMapping(value = {"/"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String login(){
        return "index";
    }

    @RequestMapping(value = {"/403"})
    public String forbidden(){
        return "403";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        Long contarUsuarios = userDetailsService.contar();
        Long contarGrupos = gruposService.contar();
        Long contarExamenes = examenesService.contar();
        Long contarTemas = temasService.contar();
        Long contarPreguntas = preguntasService.contar();
        Long contarAsignaciones = examenesAppUserService.contar();

        model.addAttribute("contarUsuarios", contarUsuarios);
        model.addAttribute("contarGrupos", contarGrupos);
        model.addAttribute("contarExamenes", contarExamenes);
        model.addAttribute("contarTemas", contarTemas);
        model.addAttribute("contarPreguntas", contarPreguntas);
        model.addAttribute("contarAsignaciones", contarAsignaciones);
        return "menu";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
