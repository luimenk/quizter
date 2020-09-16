package com.adpmx.quizter.service;

import java.util.ArrayList;
import java.util.List;

import com.adpmx.quizter.model.Usuarios;
import com.adpmx.quizter.model.UserRole;
import com.adpmx.quizter.repository.UserRoleRepository;
import com.adpmx.quizter.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
        Usuarios usuarios1 = usuariosRepository.findByUsername(username);
        List<UserRole> appUser = userRoleRepository.findAllByUsuarios_Username(username);

        //Mapear nuestra lista de Authority con la de spring security
        List grantList = new ArrayList();

        for (int i = 0; i < appUser.size(); i++) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.get(i).getRole().getRoleName());
            grantList.add(grantedAuthority);
        }

        //Crear El objeto UserDetails que va a ir en sesión y retornarlo.
        UserDetails user = (UserDetails) new User(usuarios1.getUsername(), usuarios1.getPassword(), grantList);
        return user;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Usuarios save(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    public List<UserRole> findAllByUsuarios_UserId(Long id){
        return userRoleRepository.findAllByUsuarios_UserId(id);
    }

    public Usuarios findByUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }

    public Usuarios findByUserId(Long id) {
        return usuariosRepository.findByUserId(id);
    }

    public void deleteRoles(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }

    public void deleteUsers(Usuarios id) {
        usuariosRepository.delete(id);
    }

    public long contar() {
        return usuariosRepository.count();
    }
}
