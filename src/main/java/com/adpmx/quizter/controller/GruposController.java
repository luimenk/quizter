package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.Grupos;
import com.adpmx.quizter.service.GruposService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin
public class GruposController {

    @Autowired
    private GruposService gruposService;

    private static final Logger APP = LoggerFactory.getLogger(GruposController.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/grupos"})
    public String listar(Model model){
        List<Grupos> grupos = (List<Grupos>) gruposService.findAll();
        model.addAttribute("grupos", grupos);
        return "grupos/index";
    }

    @RequestMapping(value = {"/agregarGrupo"})
    public String agregar(Model model){

        return "grupos/datos";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registrarGrupo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    //@ResponseStatus(code = HttpStatus.CREATED, reason = "Grupo Creado")
    public String registrar(@RequestParam Long idGrupo,
                            @RequestParam String nombreGrupo,
                            RedirectAttributes redirectAttributes){
        if (idGrupo == null){
            System.out.println("");
            Grupos grupo = new Grupos();
            grupo.setNombreGrupo(nombreGrupo);
            APP.info("Entró para Guardar nuevo");
            gruposService.save(grupo);
            redirectAttributes.addFlashAttribute("message", "guardado");
        } else {
            Grupos grupo = gruposService.findById(idGrupo);
            grupo.setNombreGrupo(nombreGrupo);
            APP.info("Entró para modificar");
            gruposService.save(grupo);
            redirectAttributes.addFlashAttribute("message", "modificado");
        }

        return "redirect:/grupos";
    }

    @RequestMapping("/eliminarGrupo/{id}")
    @CrossOrigin(origins = "*")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        try {
            gruposService.delete(id);
            redirectAttributes.addFlashAttribute("message", "eliminado");
        } catch (Exception ex){
            return "redirect:/grupos?error=true";
        }
        return "redirect:/grupos";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/modificarGrupo/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        Grupos grupo = gruposService.findById(id);

        model.addAttribute("idGrupo", grupo.getIdGrupo());
        model.addAttribute("nombreGrupo", grupo.getNombreGrupo());

        return "grupos/datos";
    }
}
