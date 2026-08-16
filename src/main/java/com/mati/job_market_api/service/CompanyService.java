package com.mati.job_market_api.service;

import com.mati.job_market_api.exception.ResourceNotFoundException;
import com.mati.job_market_api.model.Company;
import com.mati.job_market_api.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company companyDetails) {
        Company company = getCompanyById(id);
        company.setName(companyDetails.getName());
        return companyRepository.save(company);
    }

    public Company patchCompany(Integer id, Company companyDetails) {
        Company company = getCompanyById(id);
        if (companyDetails.getName() != null) {
            company.setName(companyDetails.getName());
        }
        return companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
