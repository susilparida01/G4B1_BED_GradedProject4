package com.gl.gradedproject.employeemanagementsystem.service;

import com.gl.gradedproject.employeemanagementsystem.dto.UserDto;
import com.gl.gradedproject.employeemanagementsystem.entity.User;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

    List<UserDto> getUsers();

    UserDto getUserByUsername(String username);
}