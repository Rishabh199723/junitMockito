package com.example.junitMockito.Controller;

import com.example.junitMockito.Model.Employee;
import com.example.junitMockito.Model.Response;
import com.example.junitMockito.Repository.EmployeeRepository;
import com.example.junitMockito.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

  /*  @Autowired
    EmployeeService employeeService;
*/


    @PostMapping("/saveEmployee")
    public Response saveEmployee(@RequestBody Employee employee) {
//       Employee employee1 = employeeService.saveEmployee(employee);
        System.out.println("repo===="+employeeRepository);
        Employee employee1 = employeeRepository.save(employee);
       System.out.println("employee---"+employee1);
       return new Response("saved users: 1", true);
    }

    public Response getEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return new Response("retrieved employees:"+employees.size(), true);
    }
}
