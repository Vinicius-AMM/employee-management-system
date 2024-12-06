package com.employee_management_jsf.service.Impl;

import com.employee_management_jsf.domain.model.Address;
import com.employee_management_jsf.domain.model.Employee;
import com.employee_management_jsf.domain.repositories.EmployeeRepository;
import com.employee_management_jsf.exceptions.BusinessException;
import com.employee_management_jsf.exceptions.EmployeeNotFoundException;
import com.employee_management_jsf.service.IEmployeeService;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressServiceImpl addressService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressServiceImpl addressService) {
        this.employeeRepository = employeeRepository;
        this.addressService = addressService;
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"));
    }

    @Override
    public Employee create(Employee employeeToCreate) {
        ofNullable(employeeToCreate).orElseThrow(() -> new BusinessException("Raw Material to create must not be null!"));
        if(ofNullable(employeeToCreate.getId()).isPresent() && employeeRepository.existsById(employeeToCreate.getId())){
            throw new BusinessException("Employee with this ID already exists!");
        }
        return employeeRepository.save(employeeToCreate);
    }

    @Override
    public Employee update(String id, Employee employeeToUpdate) {
        Employee dbEmployee = this.findById(id);
        Address dbAddress = dbEmployee.getAddress();
        if(!dbEmployee.getId().equals(employeeToUpdate.getId())){
            throw new BusinessException("Update IDs must be the same!");
        }
        dbEmployee.setFirstName(employeeToUpdate.getFirstName());
        dbEmployee.setLastName(employeeToUpdate.getLastName());
        dbEmployee.setEmail(employeeToUpdate.getEmail());
        dbEmployee.setPhoneNumber(employeeToUpdate.getPhoneNumber());
        dbEmployee.setRole(employeeToUpdate.getRole());
        dbEmployee.setAddress(addressService.update(dbAddress.getId(), employeeToUpdate.getAddress()));

        return employeeRepository.save(dbEmployee);
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }
}