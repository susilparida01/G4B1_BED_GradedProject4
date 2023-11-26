package com.gl.gradedproject.employeemanagementsystem.mapper;

import com.gl.gradedproject.employeemanagementsystem.dto.UserDto;
import com.gl.gradedproject.employeemanagementsystem.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toSet()))
                .build();
    }

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .roles(userDto.getRoles().stream().map(RoleMapper::mapToRole).collect(Collectors.toSet()))
                .build();
    }
}