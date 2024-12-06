package com.employee_management_jsf.service;

public interface ICrudService<T, ID>{
    Iterable<T> findAll();
    T findById(ID id);
    T create(T object);
    T update(ID id, T object);
    void deleteById(ID id);
}
