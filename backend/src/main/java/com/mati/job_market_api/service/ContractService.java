package com.mati.job_market_api.service;

import com.mati.job_market_api.repository.ContractRepository;
import com.mati.job_market_api.exception.ResourceNotFoundException;
import com.mati.job_market_api.model.Contract;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(Integer id){
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id " + id));
    }

    public Contract createContract(Contract contract){
        return contractRepository.save(contract);
    }

    public Contract updateContract(Integer id, Contract contractDetails){
        Contract contract = getContractById(id);
        contract.setJob(contractDetails.getJob());
        contract.setContractType(contractDetails.getContractType());
        contract.setSalaryMin(contractDetails.getSalaryMin());
        contract.setSalaryMax(contractDetails.getSalaryMax());
        return contractRepository.save(contract);
    }

    public Contract patchContract(Integer id, Contract contractDetails){
        Contract contract = getContractById(id);

        if (contractDetails.getContractType() != null) {
            contract.setContractType(contractDetails.getContractType());
        }
        if (contractDetails.getSalaryMin() != null) {
            contract.setSalaryMin(contractDetails.getSalaryMin());
        }
        if (contractDetails.getSalaryMax() != null) {
            contract.setSalaryMax(contractDetails.getSalaryMax());
        }
        if (contractDetails.getJob() != null) {
            contract.setJob(contractDetails.getJob());
        }

        return contractRepository.save(contract);
    }

    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }
}
