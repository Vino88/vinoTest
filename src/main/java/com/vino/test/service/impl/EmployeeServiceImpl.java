package com.vino.test.service.impl;

import com.vino.test.Prefix;
import com.vino.test.dto.EmployeeDto;
import com.vino.test.models.Employee;
import com.vino.test.repository.EmployeeRepository;
import com.vino.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Integer findById(Integer id) {
        return employeeRepository.findById(id).getId();
    }

    @Override
    public Page<Employee> findAllWithParam(String searchParam, Pageable pageable) {
        return employeeRepository.getPagingAllData("%"+searchParam+"%",pageable);
    }

    @Override
    public void addOrUpdateEmployee(EmployeeDto requestData) {
        if (requestData.getId() == null){
            Employee employee = new Employee();
            employee.setFullName(requestData.getFullName());
            employee.setEmail(requestData.getEmail());
            employee.setCreatedAt(new Date());
            employee.setUserStamp(Prefix.LocalComputerName());
            employeeRepository.save(employee);
        } else {
            Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(requestData.getId()));
            Employee currentData =  employee.get();
            currentData.setFullName(requestData.getFullName());
            currentData.setEmail(requestData.getEmail());
            currentData.setUpdatedAt(new Date());
            currentData.setUserStamp(Prefix.LocalComputerName());
            employeeRepository.save(currentData);
        }
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return (Page<Employee>) employeeRepository.findAll(pageable);
    }
}
