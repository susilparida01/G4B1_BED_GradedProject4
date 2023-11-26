package com.gl.gradedproject.employeemanagementsystem.controller;

import com.gl.gradedproject.employeemanagementsystem.dto.EmployeeDto;
import com.gl.gradedproject.employeemanagementsystem.service.EmployeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public List<EmployeeDto> listEmployee() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable long employeeId) {
        return ResponseEntity.ok(this.employeeService.getEmployeeDetails(employeeId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        try {
            return ResponseEntity.ok(this.employeeService.save(employeeDto));
        } catch (DataIntegrityViolationException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable long employeeId,
                                            @Valid @RequestBody EmployeeDto employeeDto) {
        try {
            return ResponseEntity.ok(this.employeeService.update(employeeId, employeeDto));
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long employeeId) {
        try {
            this.employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok("Deleted employee id - " + employeeId);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{firstName}")
    public ResponseEntity<?> searchByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(employeeService.searchByFirstName(firstName));
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sortByFirstName(@RequestParam("order") Sort.Direction order) {
        return ResponseEntity.ok(employeeService.sortByFirstName(order.isAscending()));
    }
}
