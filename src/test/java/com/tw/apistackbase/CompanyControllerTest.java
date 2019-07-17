package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import com.tw.apistackbase.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void should_return_all_companies_when_invoke_list() throws Exception {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("alibaba1", 100));
        companies.add(new Company("tengxun2", 90));

        when(companyService.findAll()).thenReturn(companies);

        mvc.perform(get("/companies")).andExpect(status().isOk()).
                andExpect(jsonPath("$[0].companyName", is("alibaba1")))
                .andExpect(jsonPath("$[1].companyName", is("tengxun2")));
    }

    @Test
    void should_return_match_company_when_given_companyName() throws Exception {
        when(companyService.findByName(anyString())).thenReturn(new Company("alibaba1", 100));

        mvc.perform(get("/companies/alibaba1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeesNumber", is(100)));
    }

    @Test
    void should_return_employees_when_given_companyName() throws Exception {
        Employee employee1 = new Employee(4, "alibaba1", 20, "male", 6000);
        Employee employee2 = new Employee(11, "tengxun2", 19, "female", 7000);
        List<Employee> employees = Arrays.asList(employee1, employee2);
        Company company = new Company("alibaba1", 2, employees);
        when(companyService.findEmployeesByName(anyString())).thenReturn(employees);

        mvc.perform(get("/companies/alibaba1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_return_page_companies_when_given_page_and_size() throws Exception {
        List<Company> companies = Arrays.asList(new Company("alibaba1", 100),
                new Company("tengxun2", 80));
        when(companyService.findByPageAndSize(anyInt(), anyInt())).thenReturn(companies);

        mvc.perform(get("/companies?page=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_add_company_when_add_given_company() throws Exception {
        Company company = new Company("alibaba1", 2);

        when(companyService.updateOrSave(ArgumentMatchers.any())).thenReturn(company);

        mvc.perform(post("/companies").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("alibaba1")));

    }

    @Test
    void should_update_company_when_add_given_company() throws Exception {
        Company company = new Company("alibaba1", 2);

        when(companyService.updateOrSave(ArgumentMatchers.any())).thenReturn(company);

        mvc.perform(put("/companies").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("alibaba1")));
    }

    @Test
    void should_delete_company_when_delete_given_companyId() throws Exception {
        mvc.perform(delete("/companies/1")).andExpect(status().isOk());
        verify(companyService).deleteById(1);
    }
}
