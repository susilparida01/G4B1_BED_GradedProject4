package com.gl.gradedproject.employeemanagementsystem.mapper;

import com.gl.gradedproject.employeemanagementsystem.dto.RoleDto;
import com.gl.gradedproject.employeemanagementsystem.entity.Role;

public class RoleMapper {
    public static RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder().id(role.getId()).name(role.getName()).build();
    }

    public static Role mapToRole(RoleDto roleDto) {
        return Role.builder().id(roleDto.getId()).name(roleDto.getName()).build();
    }
}
