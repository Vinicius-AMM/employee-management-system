package com.employee_management_jsf.service.config;

import com.employee_management_jsf.domain.repositories.AddressRepository;
import com.employee_management_jsf.service.Impl.AddressServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressServiceConfig {
    @Bean
    public AddressServiceImpl addressService(AddressRepository addressRepository){
        return new AddressServiceImpl(addressRepository);
    }
}
