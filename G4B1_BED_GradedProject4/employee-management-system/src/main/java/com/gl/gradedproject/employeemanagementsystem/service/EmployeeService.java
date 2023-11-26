package com.gl.gradedproject.employeemanagementsystem.service;

import com.gl.gradedproject.employeemanagementsystem.dto.EmployeeDto;
import com.gl.gradedproject.employeemanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeDetails(long employeeId);

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto update(long employeeId, EmployeeDto employeeDto);

    void deleteEmployee(long employeeId);

    List<EmployeeDto> searchByFirstName(String firstName);

    List<EmployeeDto> sortByFirstName(boolean isAscending);
}
