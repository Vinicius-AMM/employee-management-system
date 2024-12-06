package com.employee_management_jsf.domain.dtos;

import com.employee_management_jsf.domain.model.Address;

public record AddressDto(
        Long id,
        String street,
        String city,
        String state,
        String zipCode
) {
    public AddressDto(Address address){
        this(address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode());
    }

    public Address toModel(){
        Address address = new Address();
        address.setId(id);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        return address;
    }
}
