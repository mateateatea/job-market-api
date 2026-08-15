package com.mati.job_market_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Integer contractId;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "salary_max")
    private BigDecimal salaryMax;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId){
        this.contractId = contractId;
    }

    public Job getJob(){
        return job;
    }

    public void setJob(Job job){
        this.job = job;
    }

    public String getContractType(){
        return contractType;
    }

    public void setContractType(String contractType){
        this.contractType = contractType;
    }

    public BigDecimal getSalaryMin() { return salaryMin; }

    public void setSalaryMin(BigDecimal salaryMin) { this.salaryMin = salaryMin; }

    public BigDecimal getSalaryMax() { return salaryMax; }

    public void setSalaryMax(BigDecimal salaryMax) { this.salaryMax = salaryMax; }
}
