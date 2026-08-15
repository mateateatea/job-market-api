package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Job;
import com.mati.job_market_api.repository.JobRepository;
import org.springframework.web.bind.annotation.*;
import com.mati.job_market_api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Integer id) {
        return jobRepository.findById(id);
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Integer id, @RequestBody Job jobDetails) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));

        job.setTitle(jobDetails.getTitle());
        job.setCity(jobDetails.getCity());
        job.setWorkModel(jobDetails.getWorkModel());
        job.setSeniority(jobDetails.getSeniority());
        job.setSource(jobDetails.getSource());
        job.setDatePosted(jobDetails.getDatePosted());
        job.setLink(jobDetails.getLink());
        job.setCompany(jobDetails.getCompany());
        job.setSkills(jobDetails.getSkills());

        return jobRepository.save(job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Integer id) {
        jobRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Job patchJob(@PathVariable Integer id, @RequestBody Job jobDetails) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));

        if (jobDetails.getTitle() != null) {
            job.setTitle(jobDetails.getTitle());
        }

        if (jobDetails.getCity() != null) {
            job.setCity(jobDetails.getCity());
        }

        if (jobDetails.getWorkModel() != null) {
            job.setWorkModel(jobDetails.getWorkModel());
        }

        if (jobDetails.getSeniority() != null) {
            job.setSeniority(jobDetails.getSeniority());
        }

        if (jobDetails.getSource() != null) {
            job.setSource(jobDetails.getSource());
        }

        if (jobDetails.getDatePosted() != null) {
            job.setDatePosted(jobDetails.getDatePosted());
        }

        if (jobDetails.getLink() != null) {
            job.setLink(jobDetails.getLink());
        }

        if (jobDetails.getCompany() != null) {
            job.setCompany(jobDetails.getCompany());
        }

        if (jobDetails.getSkills() != null) {
            job.setSkills(jobDetails.getSkills());
        }

        return jobRepository.save(job);
    }
}