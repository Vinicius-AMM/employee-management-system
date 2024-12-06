package com.employee_management_jsf.infra.handler;

import com.employee_management_jsf.exceptions.AddressNotFoundException;
import com.employee_management_jsf.exceptions.BusinessException;
import com.employee_management_jsf.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    private ResponseEntity<String> emplyeeNotFoundHandler(EmployeeNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
    }

    @ExceptionHandler(AddressNotFoundException.class)
    private ResponseEntity<String> addressNotFoundHandler(AddressNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found.");
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<String> businessExceptionHandler(BusinessException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
