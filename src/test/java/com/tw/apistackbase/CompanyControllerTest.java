package com.tw.apistackbase;

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

        ResultActions resultActions = mvc.perform(get("/companies"));
        resultActions.andExpect(status().isOk()).
                andExpect(jsonPath("$[0].companyName", is("alibaba1")))
                .andExpect(jsonPath("$[1].companyName", is("tengxun2")));
    }

    @Test
    void should_return_match_company_when_given_companyName() throws Exception {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("alibaba1", 100));
        companies.add(new Company("tengxun2", 90));

        when(companyService.findByName(anyString())).thenReturn(companies.get(0));

        ResultActions resultActions = mvc.perform(get("/companies/{companyName}", "alibaba1"));
        resultActions.andExpect(status().isOk()).
                andExpect(jsonPath("$.employeesNumber", is(100)));
    }

}
