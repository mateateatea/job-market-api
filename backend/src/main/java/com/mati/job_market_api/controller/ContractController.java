package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Contract;
import com.mati.job_market_api.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("{id}")
    public Contract getContractById(@PathVariable Integer id) {
        return contractService.getContractById(id);
    }

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @PutMapping("/{id}")
    public Contract updateContract(@PathVariable Integer id, @RequestBody Contract contractDetails) {
        return contractService.updateContract(id, contractDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Integer id) {
        contractService.deleteContract(id);
    }

    @PatchMapping("/{id}")
    public Contract patchContract(@PathVariable Integer id, @RequestBody Contract contractDetails) {
        return contractService.patchContract(id, contractDetails);
    }
}