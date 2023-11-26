package com.gl.gradedproject.employeemanagementsystem.service;

import com.gl.gradedproject.employeemanagementsystem.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto save(RoleDto role);

    RoleDto findRole(RoleDto roleDto);

    List<RoleDto> findAllRoles();
}
