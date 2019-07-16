package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)

public class EmployeeContollerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void should_return_list_when_invoke_list() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));

        when(employeeService.findAll()).thenReturn(employees);

        ResultActions resultActions = mvc.perform(get("/employees"));
        resultActions.andExpect(status().isOk()).
                andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[1].id", is(11)));
    }

    @Test
    void should_return_match_employee_when_invoke_findById() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));

        when(employeeService.findById(anyInt())).thenReturn(employees.get(0));

        ResultActions resultActions = mvc.perform(get("/employees/{employeeId}", 4));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(4)));
    }

    @Test
    void should_return_list_when_invoke_by_page_and_size() throws Exception {
        Employee employee1 = new Employee(4, "alibaba1", 20, "male", 6000);
        Employee employee2 = new Employee(11, "tengxun2", 19, "female", 7000);
        Employee employee3 = new Employee(6, "alibaba3", 19, "male", 8000);
        Employee employee4 = new Employee(18, "huiwei1", 20, "male", 8000);
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4));
        List<Employee> filterEmployees = new ArrayList<>(Arrays.asList(employee3, employee4));


        when(employeeService.findByPageAndSize(anyInt(), anyInt())).thenReturn(filterEmployees);

        ResultActions resultActions = mvc.perform(get("/employees?page={page}&pageSize={pageSize}", 2, 2));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(6)))
                .andExpect(jsonPath("$[1].id", is(18)));
    }

    @Test
    void should_return_match_gender_list_when_invoke_by_gender() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));


        when(employeeService.findByGender(anyString())).thenReturn(employees);

        ResultActions resultActions = mvc.perform(get("/employees?gender={gender}", "female"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(11)))
                .andExpect(jsonPath("$[1].id", is(11)));

    }

    @Test
    void should_add_employee_when_add_given_employee() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));


        Employee employee = new Employee(6, "alibaba3", 19, "male", 8000);
        Class classz = Employee.class;

        //when(employeeService.updateOrSave(any(Class<Employee>))).thenReturn(employee);

        ResultActions resultActions = mvc.perform(get("/employees?gender={gender}", "female"));
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$[2].id", is(6)));

    }

    @Test
    void should_update_employee_when_add_given_employee() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));


        Employee employee = new Employee(6, "alibaba3", 19, "male", 8000);
        Class classz = Employee.class;

        //when(employeeService.updateOrSave(any(Class<Employee>))).thenReturn(employee);

        ResultActions resultActions = mvc.perform(get("/employees?gender={gender}", "female"));
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$[2].id", is(6)));

    }

    @Test
    void should_delete_employee_when_delete_given_employeeId() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));



        //when(employeeService.deleteById(anyInt())).thenReturn(employees);

        ResultActions resultActions = mvc.perform(get("/employees?gender={gender}", "female"));
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$[2].id", is(6)));

    }

}
