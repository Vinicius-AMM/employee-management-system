package com.employee_management_jsf.exceptions;

public class AddressNotFoundException extends BusinessException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}