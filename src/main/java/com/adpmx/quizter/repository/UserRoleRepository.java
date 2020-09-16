package com.adpmx.quizter.repository;

import java.util.List;

import com.adpmx.quizter.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    public List<UserRole> findAllByUsuarios_Username(String username);
    public List<UserRole> findAllByUsuarios_UserId(Long id);
}
