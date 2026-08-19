package com.mati.job_market_api.repository;

import com.mati.job_market_api.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}