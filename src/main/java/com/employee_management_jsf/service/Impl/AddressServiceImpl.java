package com.employee_management_jsf.service.Impl;

import com.employee_management_jsf.domain.model.Address;
import com.employee_management_jsf.domain.repositories.AddressRepository;
import com.employee_management_jsf.exceptions.AddressNotFoundException;
import com.employee_management_jsf.exceptions.BusinessException;
import com.employee_management_jsf.service.IAddressService;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Iterable<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Address not found!"));
    }

    @Override
    public Address create(Address addressToCreate) {
        ofNullable(addressToCreate).orElseThrow(() -> new BusinessException("Address to create must not be null!"));
        if(ofNullable(addressToCreate.getId()).isPresent() && addressRepository.existsById(addressToCreate.getId())){
            throw new BusinessException("Employee with this ID already exists!");
        }
        return addressRepository.save(addressToCreate);
    }

    @Override
    public Address update(Long id, Address addressToUpdate) {
        Address dbAddress = this.findById(id);
        if(!dbAddress.getId().equals(addressToUpdate.getId())){
            throw new BusinessException("Employee IDs must be the same!");
        }
        dbAddress.setStreet(addressToUpdate.getStreet());
        dbAddress.setCity(addressToUpdate.getCity());
        dbAddress.setState(addressToUpdate.getState());
        dbAddress.setZipCode(addressToUpdate.getZipCode());

        return addressRepository.save(dbAddress);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}