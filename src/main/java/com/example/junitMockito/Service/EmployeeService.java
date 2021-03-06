package com.example.junitMockito.Service;

import com.example.junitMockito.Model.Employee;
import com.example.junitMockito.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        System.out.println("In save.................");
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        System.out.println("In Get employee.................");
        System.out.println("employee repo==="+employeeRepository);
        return employeeRepository.getById(id);
    }
}
