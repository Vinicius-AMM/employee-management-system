package com.employee_management_jsf.controllers;

import com.employee_management_jsf.domain.dtos.EmployeeDto;
import com.employee_management_jsf.domain.model.Employee;import com.employee_management_jsf.service.Impl.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeService.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(EmployeeDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(new EmployeeDto(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeToCreate) {
        Employee employee = employeeService.create(employeeToCreate.toModel());
        return ResponseEntity.ok(new EmployeeDto(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String id, @RequestBody EmployeeDto employeeToUpdate) {
        Employee employee = employeeService.update(id, employeeToUpdate.toModel());
        return ResponseEntity.ok(new EmployeeDto(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}