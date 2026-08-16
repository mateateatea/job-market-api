package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Company;
import com.mati.job_market_api.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company companyDetails){
        return companyService.updateCompany(id, companyDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id){
        companyService.deleteCompany(id);
    }

    @PatchMapping("/{id}")
    public Company patchCompany(@PathVariable Integer id, @RequestBody Company companyDetails) {
        return companyService.patchCompany(id, companyDetails);
    }
}