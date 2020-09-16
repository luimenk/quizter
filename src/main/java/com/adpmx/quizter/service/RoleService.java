package com.adpmx.quizter.service;

import com.adpmx.quizter.model.Respuestas;
import com.adpmx.quizter.model.Role;
import com.adpmx.quizter.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long id){
        return roleRepository.findByRoleId(id);
    }

    public Role findByName(String rolename){
        return roleRepository.findByRoleName(rolename);
    }
}
