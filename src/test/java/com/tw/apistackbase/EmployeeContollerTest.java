package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
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
    void should_return_first_when_invoke_list() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));

        when(employeeService.findById(anyInt())).thenReturn(employees.get(0));

        ResultActions resultActions = mvc.perform(get("/employees/{employeeId}", 4));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(4)));
    }
}
