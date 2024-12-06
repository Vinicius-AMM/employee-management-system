package com.employee_management_jsf.service.config;

import com.employee_management_jsf.domain.repositories.EmployeeRepository;
import com.employee_management_jsf.service.Impl.AddressServiceImpl;
import com.employee_management_jsf.service.Impl.EmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeServiceConfig {
    @Bean
    public EmployeeServiceImpl employeeService(EmployeeRepository employeeRepository, AddressServiceImpl addressService) {
        return new EmployeeServiceImpl(employeeRepository, addressService);
    }
}
