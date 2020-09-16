package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.Temas;
import com.adpmx.quizter.service.TemasService;

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
public class TemasController {

    @Autowired
    private TemasService temasService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/temas"})
    public String listar(Model model){
        List<Temas> temas = (List<Temas>) temasService.findAll();
        model.addAttribute("temas", temas);
        return "temas/index";
    }

    @RequestMapping(value = {"/agregarTema"})
    public String agregar(Model model){

        return "temas/datos";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registrarTema", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    //@ResponseStatus(code = HttpStatus.CREATED)
    public String registrar(@RequestParam Long idTema,
                            @RequestParam String nombreTema){

        Temas tema = new Temas();
        tema.setNombreTema(nombreTema);

        temasService.save(tema);

        return "redirect:/temas";
    }

    @RequestMapping("/eliminarTema/{id}")
    @CrossOrigin(origins = "*")
    public String delete(@PathVariable Long id) {

        try {
            temasService.delete(id);
        } catch (Exception ex){
            return "redirect:/temas?error=true";
        }
        return "redirect:/temas";
    }
}
