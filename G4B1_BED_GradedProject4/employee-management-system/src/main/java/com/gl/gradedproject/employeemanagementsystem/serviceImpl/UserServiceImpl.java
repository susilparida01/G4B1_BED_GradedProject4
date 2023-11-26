package com.gl.gradedproject.employeemanagementsystem.serviceImpl;

import com.gl.gradedproject.employeemanagementsystem.dto.RoleDto;
import com.gl.gradedproject.employeemanagementsystem.dto.UserDto;
import com.gl.gradedproject.employeemanagementsystem.mapper.UserMapper;
import com.gl.gradedproject.employeemanagementsystem.repository.UserRepository;
import com.gl.gradedproject.employeemanagementsystem.service.RoleService;
import com.gl.gradedproject.employeemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userDto.getRoles() != null) {
            Set<RoleDto> userRoles = userDto.getRoles()
                    .stream()
                    .map(roleService::findRole)
                    .collect(Collectors.toSet());
            if (userRoles.contains(null)) {
                throw new RuntimeException("No Roles Matching available. please provide valid Role");
            }
            userDto.setRoles(userRoles);
        }
        return UserMapper.mapToUserDto(userRepository.save(UserMapper.mapToUser(userDto)));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return UserMapper.mapToUserDto(userRepository.getUserByUsername(username));
    }
}
