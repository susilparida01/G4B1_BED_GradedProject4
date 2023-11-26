package com.gl.gradedproject.employeemanagementsystem.repository;

import com.gl.gradedproject.employeemanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRolesByName(String name);
    Optional<Role> getRolesByIdAndName(long id, String name);
}
