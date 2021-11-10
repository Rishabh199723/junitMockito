package com.example.junitMockito.ControllerTest;

import com.example.junitMockito.Controller.EmployeeController;
import com.example.junitMockito.Model.Employee;
import com.example.junitMockito.Model.Response;
import com.example.junitMockito.Repository.EmployeeRepository;
import com.example.junitMockito.Service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RestControllerTest {

    @MockBean
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeController employeeController;

    @Autowired
    public MockMvc mockMvc;

    /*@MockBean
    EmployeeService employeeService;*/
    /*@Autowired
    public static WebApplicationContext webApplicationContext;*/

    ObjectMapper objectMapper = new ObjectMapper();

    /*@BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

    }*/

    @Test
    public void addEmployee() throws Exception {
        Employee employee = new Employee(1L, "Rish", 23);
        String data = objectMapper.writeValueAsString(employee);
        when(employeeRepository.save(ArgumentMatchers.any())).thenReturn(employee);
//        when(employeeService.saveEmployee(ArgumentMatchers.any())).thenReturn(employee);
        MvcResult result = mockMvc.perform(post("/saveEmployee")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String stringdata = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(stringdata, Response.class);
        Assert.assertEquals(response.getStatus(), true);
    }
}