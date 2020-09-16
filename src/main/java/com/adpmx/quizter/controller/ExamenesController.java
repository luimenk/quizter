package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.Examenes;
import com.adpmx.quizter.model.Preguntas;
import com.adpmx.quizter.model.PreguntasExamenes;
import com.adpmx.quizter.model.Temas;
import com.adpmx.quizter.service.ExamenesService;

import com.adpmx.quizter.service.PreguntasService;
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
public class ExamenesController {

    @Autowired
    private ExamenesService examenesService;

    @Autowired
    private TemasService temasService;

    @Autowired
    private PreguntasService preguntasService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/examenes"})
    public String listar(Model model){
        List<Examenes> examenes = (List<Examenes>) examenesService.findAll();
        model.addAttribute("examenes", examenes);
        return "examenes/index";
    }

    @RequestMapping(value = {"/agregarExamen"})
    public String agregar(Model model){
        List<Temas> temas = (List<Temas>) temasService.findAll();
        model.addAttribute("temas", temas);

        return "examenes/datos";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registrarExamen", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    //@ResponseStatus(code = HttpStatus.CREATED)
    public String registrar(@RequestParam Long idExamen,
                            @RequestParam String nombreExamen,
                            @RequestParam Long idTema,
                            @RequestParam Long cantidadPreguntas) {
        long cantidadActual = preguntasService.contarAllByIdTema(idTema);
        if (cantidadActual < cantidadPreguntas){
            return "redirect:/agregarExamen?error=true";
        } else {
            Examenes examen = new Examenes();
            examen.setNombreExamen(nombreExamen);
            examenesService.save(examen);

            examen.setIdExamen(examenesService.findByNombreExamen(nombreExamen).getIdExamen());

            List<Preguntas> preguntas = preguntasService.findRandPreguntas(idTema, cantidadPreguntas);

            for (int i = 0; i < preguntas.size(); i++){
                PreguntasExamenes preguntasExamenes= new PreguntasExamenes();
                preguntasExamenes.setExamen(examen);
                preguntasExamenes.setPregunta(preguntas.get(i));
                preguntasService.saveExamenes(preguntasExamenes);
            }

            return "redirect:/examenes";
        }
    }

    @RequestMapping("/eliminarExamen/{id}")
    @CrossOrigin(origins = "*")
    public String delete(@PathVariable Long id) {

        try {
            examenesService.delete(id);
        } catch (Exception ex){
            return "redirect:/examenes?error=true";
        }
        return "redirect:/examenes";
    }
}
