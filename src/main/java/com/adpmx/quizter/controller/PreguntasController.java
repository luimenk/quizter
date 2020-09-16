package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.*;
import com.adpmx.quizter.service.PreguntasService;
import com.adpmx.quizter.service.RespuestasService;
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
public class PreguntasController {

    @Autowired
    private PreguntasService preguntasService;

    @Autowired
    private TemasService temasService;

    @Autowired
    private RespuestasService respuestasService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/preguntas"})
    public String listar(Model model){
        List<Preguntas> preguntas = (List<Preguntas>) preguntasService.findAll();
        List<Respuestas> respuestas = (List<Respuestas>) respuestasService.findAll();
        model.addAttribute("preguntas", preguntas);
        return "preguntas/index";
    }

    @RequestMapping(value = {"/agregarPregunta"})
    public String agregar(Model model){
        List<Temas> temas = (List<Temas>) temasService.findAll();
        List<Respuestas> respuestas = (List<Respuestas>) respuestasService.findAll();

        model.addAttribute("respuestas", respuestas);
        model.addAttribute("temas", temas);

        return "preguntas/datos";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registrarPregunta", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public String registrar(@RequestParam Long idPregunta,
                            @RequestParam String dpregunta,
                            @RequestParam Long idTema,
                            @RequestParam String respuestaa,
                            @RequestParam String respuestab,
                            @RequestParam String respuestac,
                            @RequestParam String respuestad,
                            @RequestParam(value = "ra", required = false) boolean ra,
                            @RequestParam(value = "rb", required = false) boolean rb,
                            @RequestParam(value = "rc", required = false) boolean rc,
                            @RequestParam(value = "rd", required = false) boolean rd){

        Preguntas pregunta = new Preguntas();
        String[] arregloRespuestas = new String[4];
        arregloRespuestas[0] = respuestaa;
        arregloRespuestas[1] = respuestab;
        arregloRespuestas[2] = respuestac;
        arregloRespuestas[3] = respuestad;

        Boolean[] arregloisCorrect = new Boolean[4];
        arregloisCorrect[0] = ra;
        arregloisCorrect[1] = rb;
        arregloisCorrect[2] = rc;
        arregloisCorrect[3] = rd;

        pregunta.setPregunta(dpregunta);
        pregunta.setTema(temasService.findById(idTema));

        preguntasService.save(pregunta);

        pregunta.setIdPregunta(preguntasService.findByPregunta(dpregunta).getIdPregunta());

        for (int i = 0; i < 4; i++){
            Respuestas respuesta = new Respuestas();
            respuesta.setRespuesta(arregloRespuestas[i]);
            respuesta.setValor("arregloisCorrect[i]");
            respuesta.setPregunta(pregunta);
            respuestasService.save(respuesta);
        }

        return "redirect:/preguntas";
    }

    @RequestMapping("/eliminarPregunta/{id}")
    @CrossOrigin(origins = "*")
    public String delete(@PathVariable Long id) {
        List<Respuestas> respuestas = respuestasService.findAllByIdPregunta(id);
        for (int i = 0; i<respuestas.size(); i++){
            respuestasService.delete(respuestas.get(i).getIdRespuesta());
        }

        Preguntas pregunta = preguntasService.findById(id);
        preguntasService.delete(pregunta.getIdPregunta());

        return "redirect:/preguntas";

    }
}
