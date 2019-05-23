package com.vino.test.repository;


import com.vino.test.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(
            value = "select employee from Employee employee where employee.fullName like :param or employee.id like :param "
    )
    Page<Employee> getPagingAllData(String s, Pageable pageable);


    @Query(
            value = "select employee from Employee employee where employee.email is NOT null"
    )
    Employee findAllByEmail();

}
