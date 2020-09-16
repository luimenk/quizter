package com.adpmx.quizter.controller;

import com.adpmx.quizter.model.Grupos;
import com.adpmx.quizter.model.Role;
import com.adpmx.quizter.model.UserRole;
import com.adpmx.quizter.model.Usuarios;
import com.adpmx.quizter.service.RoleService;
import com.adpmx.quizter.service.UserDetailsServiceImpl;
import com.adpmx.quizter.service.GruposService;
import com.adpmx.quizter.service.MailService;

import com.adpmx.quizter.utils.EncryptedPasswordUtils;
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
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GruposService gruposService;

    @Autowired
    private MailService mailService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = {"/usuarios"})
    public String listar(Model model){
        List<Usuarios> usuarios = (List<Usuarios>) userDetailsService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/index";
    }

    @RequestMapping(value = {"/agregarUsuario"})
    public String agregar(Model model){

        List<Role> roles = roleService.findAll();
        List<Grupos> grupos = gruposService.findAll();

        model.addAttribute("grupos", grupos);
        model.addAttribute("roles", roles);

        return "usuarios/datos";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/registrarUsuario", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    //@ResponseStatus(code = HttpStatus.CREATED)
    public String registrar(@RequestParam Long id,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam Long idGrupo,
                            @RequestParam Long tipoUsuario){

        Usuarios usuario = new Usuarios();
        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();

        usuario.setUsername(username);
        usuario.setPassword(encryptedPasswordUtils.encryte(password));
        usuario.setEnabled(true);
        usuario.setGrupo(gruposService.findById(idGrupo));

        userDetailsService.save(usuario);

        Usuarios nuevaUnion = userDetailsService.findByUsername(username);
        Role role = roleService.findByName("ROLE_ADMIN");
        Role role2 = roleService.findByName("ROLE_USER");
        UserRole userRole = new UserRole();

        if (tipoUsuario == 1){
            userRole.setRole(role);
            userRole.setUsuarios(nuevaUnion);

            userDetailsService.saveUserRole(userRole);

            userRole = new UserRole();

            userRole.setRole(role2);
            userRole.setUsuarios(nuevaUnion);

            userDetailsService.saveUserRole(userRole);

            mailService.userRegistered(username, password);

            return "redirect:/usuarios?logout";

        } else if (tipoUsuario == 2) {
            userRole = new UserRole();

            userRole.setRole(role2);
            userRole.setUsuarios(nuevaUnion);

            userDetailsService.saveUserRole(userRole);

            mailService.userRegistered(username, password);

            return "redirect:/usuarios?logout";
        }

        return "redirect:/usuarios?logout";
    }

    @RequestMapping("/eliminarUsuario/{id}")
    @CrossOrigin(origins = "*")
    public String delete(@PathVariable Long id) {
        List<UserRole> roles = userDetailsService.findAllByUsuarios_UserId(id);
        for (int i = 0; i<roles.size(); i++){
            userDetailsService.deleteRoles(roles.get(i));
        }
        Usuarios usuario = userDetailsService.findByUserId(id);
        userDetailsService.deleteUsers(usuario);

        return "redirect:/usuarios";

    }
}
