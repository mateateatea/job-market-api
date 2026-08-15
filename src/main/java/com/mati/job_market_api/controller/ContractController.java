package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Contract;
import com.mati.job_market_api.repository.ContractRepository;
import org.springframework.web.bind.annotation.*;
import com.mati.job_market_api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractRepository contractRepository;

    public ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Contract> getContractById(@PathVariable Integer id) {
        return contractRepository.findById(id);
    }

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractRepository.save(contract);
    }

    @PutMapping("/{id}")
    public Contract updateContract(@PathVariable Integer id, @RequestBody Contract contractDetails) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id " + id));

        contract.setJob(contractDetails.getJob());
        contract.setContractType(contractDetails.getContractType());
        contract.setSalaryMin(contractDetails.getSalaryMin());
        contract.setSalaryMax(contractDetails.getSalaryMax());

        return contractRepository.save(contract);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Integer id) { contractRepository.deleteById(id);}
}