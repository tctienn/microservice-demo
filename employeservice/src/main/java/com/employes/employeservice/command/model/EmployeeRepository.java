package com.employes.employeservice.command.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employes.employeservice.command.data.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
