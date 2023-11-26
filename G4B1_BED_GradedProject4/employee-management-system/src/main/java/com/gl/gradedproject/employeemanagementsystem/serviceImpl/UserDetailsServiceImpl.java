package com.gl.gradedproject.employeemanagementsystem.serviceImpl;

import com.gl.gradedproject.employeemanagementsystem.dto.UserDto;
import com.gl.gradedproject.employeemanagementsystem.security.ApplicationUserDetails;
import com.gl.gradedproject.employeemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new ApplicationUserDetails(user);
    }
}
