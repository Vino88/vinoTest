package com.vino.test.service;

import com.vino.test.dto.EmployeeDto;
import com.vino.test.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Integer findById(Integer id);

    Iterable<Employee>findAll();

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllWithParam(String searchParam, Pageable pageable);

    void addOrUpdateEmployee(EmployeeDto requestData);

    void delete(Integer id);

}
