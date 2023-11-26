package com.gl.gradedproject.employeemanagementsystem.controller;

import com.gl.gradedproject.employeemanagementsystem.dto.UserDto;
import com.gl.gradedproject.employeemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    final private UserService userService;

    @PostMapping("/save")
    public UserDto saveUserDetails(@Valid @RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping("/list")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }
}
