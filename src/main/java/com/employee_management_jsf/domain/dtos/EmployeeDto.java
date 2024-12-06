package com.employee_management_jsf.domain.dtos;

import com.employee_management_jsf.domain.model.Address;
import com.employee_management_jsf.domain.model.Employee;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String role,
        Address address
) {
    public EmployeeDto(Employee employee){
        this(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getRole(),
                employee.getAddress());
    }

    public Employee toModel(){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setRole(role);
        employee.setAddress(address);
        return employee;
    }
}
