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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import com.mati.job_market_api.exception.ResourceNotFoundException;

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

    @Test
    void getJobById_returnsJob_whenJobExists() {
        Job job = new Job();
        job.setJobId(1);
        job.setTitle("QA Engineer");

        when(jobRepository.findById(1)).thenReturn(Optional.of(job));

        Job result = jobService.getJobById(1);

        assertEquals("QA Engineer", result.getTitle());
    }

    @Test
    void getJobById_throwsException_whenJobDoesNotExist() {
        when(jobRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            jobService.getJobById(99);
        });
    }

    @Test
    void createJob_savesAndReturnsJob() {
        Job job = new Job();
        job.setTitle("Backend Developer");

        when(jobRepository.save(job)).thenReturn(job);

        Job result = jobService.createJob(job);

        assertEquals("Backend Developer", result.getTitle());
    }

    @Test
    void patchJob_updatesTitle_whenTitleProvided() {
        Job existingJob = new Job();
        existingJob.setJobId(1);
        existingJob.setTitle("QA Engineer");

        Job jobDetails = new Job();
        jobDetails.setTitle("Senior QA Engineer");

        when(jobRepository.findById(1)).thenReturn(Optional.of(existingJob));
        when(jobRepository.save(existingJob)).thenReturn(existingJob);

        Job result = jobService.patchJob(1, jobDetails);

        assertEquals("Senior QA Engineer", result.getTitle());
    }

    @Test
    void patchJob_keepsOriginalTitle_whenTitleIsNull() {
        Job existingJob = new Job();
        existingJob.setJobId(1);
        existingJob.setTitle("QA Engineer");

        Job jobDetails = new Job();
        jobDetails.setTitle(null);

        when(jobRepository.findById(1)).thenReturn(Optional.of(existingJob));
        when(jobRepository.save(existingJob)).thenReturn(existingJob);

        Job result = jobService.patchJob(1, jobDetails);

        assertEquals("QA Engineer", result.getTitle());
    }

    @Test
    void deleteJob_callsRepositoryDeleteById() {
        jobService.deleteJob(1);

        verify(jobRepository).deleteById(1);
    }
}