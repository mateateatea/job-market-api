package com.mati.job_market_api.service;

import com.mati.job_market_api.model.Company;
import com.mati.job_market_api.model.Skill;
import com.mati.job_market_api.model.Job;
import com.mati.job_market_api.repository.JobRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    void updateJob_updatesCompanyAndSkills() {
        Job existingJob = new Job();
        existingJob.setJobId(1);

        Company company = new Company();
        company.setCompanyId(5);

        Set<Skill> skills = new HashSet<>();
        Skill skill = new Skill();
        skill.setId(2);
        skills.add(skill);

        Job jobDetails = new Job();
        jobDetails.setTitle("QA Engineer");
        jobDetails.setCompany(company);
        jobDetails.setSkills(skills);

        when(jobRepository.findById(1)).thenReturn(Optional.of(existingJob));
        when(jobRepository.save(existingJob)).thenReturn(existingJob);

        Job result = jobService.updateJob(1, jobDetails);

        assertEquals("QA Engineer", result.getTitle());
        assertEquals(company, result.getCompany());
        assertEquals(skills, result.getSkills());
    }
}