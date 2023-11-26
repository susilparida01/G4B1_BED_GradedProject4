package com.gl.gradedproject.employeemanagementsystem.controller;

import com.gl.gradedproject.employeemanagementsystem.dto.RoleDto;
import com.gl.gradedproject.employeemanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/save")
    public RoleDto saveRoleDetails(@Valid @RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @GetMapping("/list")
    public List<RoleDto> getAllRoles() {
        return roleService.findAllRoles();
    }
}
