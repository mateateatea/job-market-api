package com.mati.job_market_api.service;

import com.mati.job_market_api.model.Company;
import com.mati.job_market_api.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.mati.job_market_api.exception.ResourceNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest{

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void getCompanyById_returnsCompany_whenCompanyExists(){
        Company company = new Company();
        company.setCompanyId(1);
        company.setName("Microsoft");

        when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        Company result = companyService.getCompanyById(1);

        assertEquals("Microsoft", result.getName());
    }

    @Test
    void getCompanyById_throwsException_whenCompanyDoesNotExist(){
        when(companyRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->{
            companyService.getCompanyById(99);
        });
    }

    @Test
    void createCompany_savesAndReturnsCompany(){
        Company company = new Company();
        company.setName("Google");

        when(companyRepository.save(company)).thenReturn(company);

        Company result = companyService.createCompany(company);

        assertEquals("Google", result.getName());
    }

    @Test
    void updateCompany_updatesFields_whenCompanyExists(){
        Company existingCompany = new Company();
        existingCompany.setCompanyId(1);
        existingCompany.setName("Apple");

        Company companyDetails = new Company();
        companyDetails.setName("NVIDIA");

        when(companyRepository.findById(1)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(existingCompany)).thenReturn(existingCompany);

        Company result = companyService.updateCompany(1, companyDetails);

        assertEquals("NVIDIA", result.getName());
    }
}