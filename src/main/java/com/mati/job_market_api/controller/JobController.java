package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Job;
import com.mati.job_market_api.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                .orElseThrow(() -> new RuntimeException("Job not found with id " + id));

        job.setTitle(jobDetails.getTitle());
        job.setCity(jobDetails.getCity());
        job.setWorkModel(jobDetails.getWorkModel());
        job.setSeniority(jobDetails.getSeniority());
        job.setSource(jobDetails.getSource());
        job.setDatePosted(jobDetails.getDatePosted());
        job.setLink(jobDetails.getLink());
        job.setCompany(jobDetails.getCompany());

        return jobRepository.save(job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Integer id) {
        jobRepository.deleteById(id);
    }
}