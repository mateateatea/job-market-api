package com.mati.job_market_api.repository;

import com.mati.job_market_api.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer>{
}
