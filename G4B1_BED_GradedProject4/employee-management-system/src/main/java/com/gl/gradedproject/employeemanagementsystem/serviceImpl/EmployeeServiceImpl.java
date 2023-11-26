package com.gl.gradedproject.employeemanagementsystem.serviceImpl;

import com.gl.gradedproject.employeemanagementsystem.dto.EmployeeDto;
import com.gl.gradedproject.employeemanagementsystem.entity.Employee;
import com.gl.gradedproject.employeemanagementsystem.mapper.EmployeeMapper;
import com.gl.gradedproject.employeemanagementsystem.repository.EmployeeRepository;
import com.gl.gradedproject.employeemanagementsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeDetails(long employeeId) {
        return this.employeeRepository.findById(employeeId)
                .map(EmployeeMapper::mapToEmployeeDto)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id - " + employeeId + " not present"));
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        try {
            Employee emp = this.employeeRepository.save(EmployeeMapper.mapToEmployee(employeeDto));
            return EmployeeMapper.mapToEmployeeDto(emp);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Email Should be Unique. Please try with different email id");
        }
    }

    @Override
    public EmployeeDto update(long employeeId, EmployeeDto employeeDto) {
        try {
            Employee emp = employeeRepository.findById(employeeId).get();
            emp.setFirstName(employeeDto.getFirstName());
            emp.setLastName(employeeDto.getLastName());
            emp.setEmail(employeeDto.getEmail());
            return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(emp));
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException("Employee with id - " + employeeId + " not present");
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Email Should be Unique. Please try with different email id");
        }
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Optional<Employee> employee = this.employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            this.employeeRepository.deleteById(employeeId);
            return;
        }
        throw new EntityNotFoundException("Employee with id " + employeeId + " not Present");
    }

    @Override
    public List<EmployeeDto> searchByFirstName(String firstName) {
        return employeeRepository.getEmployeesByFirstName(firstName).stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> sortByFirstName(boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return employeeRepository.findAll(Sort.by(direction, "firstName")).stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }
}
