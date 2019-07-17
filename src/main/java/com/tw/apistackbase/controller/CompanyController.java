package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> list() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{companyName}")
    public Company findByName(@PathVariable String companyName) {
        return companyService.findByName(companyName);
    }

    @GetMapping("/companies/{companyName}/employees")
    public List<Employee> findEmployeesByName(@PathVariable String companyName) {
        return companyService.findEmployeesByName(companyName);
    }

    @GetMapping(value = "/companies", params = {"page", "pageSize"})
    public List<Company> getByPageAndSize(@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize) {
        return companyService.findByPageAndSize(page, pageSize);
    }

    @RequestMapping(value = "/companies", method = {RequestMethod.POST, RequestMethod.PUT})
    public Company updateOrSave(@RequestBody Company Company) {
        return companyService.updateOrSave(Company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void delete(@PathVariable int companyId) {
        companyService.deleteById(companyId);
    }
}
