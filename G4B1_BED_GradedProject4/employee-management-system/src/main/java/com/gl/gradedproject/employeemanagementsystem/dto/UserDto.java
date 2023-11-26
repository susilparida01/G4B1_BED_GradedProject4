package com.gl.gradedproject.employeemanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
public class UserDto {

    private long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private Set<RoleDto> roles;
}
