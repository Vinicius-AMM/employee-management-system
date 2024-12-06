package com.employee_management_jsf.exceptions;

public class EmployeeNotFoundException extends BusinessException{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
