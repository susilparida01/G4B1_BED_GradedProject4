package com.gl.gradedproject.employeemanagementsystem.serviceImpl;

import com.gl.gradedproject.employeemanagementsystem.dto.RoleDto;
import com.gl.gradedproject.employeemanagementsystem.entity.Role;
import com.gl.gradedproject.employeemanagementsystem.mapper.RoleMapper;
import com.gl.gradedproject.employeemanagementsystem.repository.RoleRepository;
import com.gl.gradedproject.employeemanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    final private RoleRepository roleRepository;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = RoleMapper.mapToRole(roleDto);
        return RoleMapper.mapToRoleDto(roleRepository.save(role));
    }

    @Override
    public RoleDto findRole(RoleDto roleDto) {
        Optional<Role> role;
        if (roleDto.getId() != 0 && roleDto.getName() != null && !roleDto.getName().isEmpty()) {
            role = this.roleRepository.getRolesByIdAndName(roleDto.getId(), roleDto.getName());
        } else if (roleDto.getId() != 0) {
            role = this.roleRepository.findById(roleDto.getId());
        } else if (roleDto.getName() != null && !roleDto.getName().isEmpty()) {
            role = this.roleRepository.getRolesByName(roleDto.getName());
        } else {
            throw new RuntimeException("Provide valid role details");
        }

        return role.map(RoleMapper::mapToRoleDto).orElse(null);
    }

    @Override
    public List<RoleDto> findAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::mapToRoleDto)
                .collect(Collectors.toList());
    }
}
