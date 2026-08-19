package com.mati.job_market_api.service;

import com.mati.job_market_api.model.Contract;
import com.mati.job_market_api.model.Job;
import com.mati.job_market_api.repository.ContractRepository;
import com.mati.job_market_api.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService;

    @Test
    void getContractById_returnsContract_whenContractExists() {
        Contract contract = new Contract();
        contract.setContractType("B2B");

        when(contractRepository.findById(1)).thenReturn(Optional.of(contract));

        Contract result = contractService.getContractById(1);

        assertEquals("B2B", result.getContractType());
    }

    @Test
    void getContractById_throwsException_whenContractDoesNotExist() {
        when(contractRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            contractService.getContractById(99);
        });
    }

    @Test
    void createContract_savesAndReturnsContract() {
        Contract contract = new Contract();
        contract.setContractType("UoP");

        when(contractRepository.save(contract)).thenReturn(contract);

        Contract result = contractService.createContract(contract);

        assertEquals("UoP", result.getContractType());
    }

    @Test
    void updateContract_updatesSalaryRange() {
        Contract existingContract = new Contract();
        existingContract.setContractType("B2B");
        existingContract.setSalaryMin(new BigDecimal("8000"));
        existingContract.setSalaryMax(new BigDecimal("12000"));

        Job job = new Job();
        job.setJobId(1);

        Contract contractDetails = new Contract();
        contractDetails.setContractType("UoP");
        contractDetails.setSalaryMin(new BigDecimal("9000"));
        contractDetails.setSalaryMax(new BigDecimal("13000"));
        contractDetails.setJob(job);

        when(contractRepository.findById(1)).thenReturn(Optional.of(existingContract));
        when(contractRepository.save(existingContract)).thenReturn(existingContract);

        Contract result = contractService.updateContract(1, contractDetails);

        assertEquals("UoP", result.getContractType());
        assertEquals(0, new BigDecimal("9000").compareTo(result.getSalaryMin()));
        assertEquals(0, new BigDecimal("13000").compareTo(result.getSalaryMax()));
        assertEquals(job, result.getJob());
    }

    @Test
    void patchContract_keepsOriginalValues_whenDetailsAreNull() {
        Contract existingContract = new Contract();
        existingContract.setContractType("B2B");
        existingContract.setSalaryMin(new BigDecimal("8000"));
        existingContract.setSalaryMax(new BigDecimal("12000"));

        Contract contractDetails = new Contract();

        when(contractRepository.findById(1)).thenReturn(Optional.of(existingContract));
        when(contractRepository.save(existingContract)).thenReturn(existingContract);

        Contract result = contractService.patchContract(1, contractDetails);

        assertEquals("B2B", result.getContractType());
        assertEquals(0, new BigDecimal("8000").compareTo(result.getSalaryMin()));
    }

    @Test
    void deleteContract_callsRepositoryDeleteById() {
        contractService.deleteContract(1);

        verify(contractRepository).deleteById(1);
    }
}