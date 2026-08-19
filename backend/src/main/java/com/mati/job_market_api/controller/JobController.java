package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Job;
import com.mati.job_market_api.service.JobService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Integer id){
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Integer id, @RequestBody Job jobDetails){
        return jobService.updateJob(id, jobDetails);
    }

    @DeleteMapping("/{id}")
    public void  deleteJob(@PathVariable Integer id){
        jobService.deleteJob(id);
    }

    @PatchMapping("/{id}")
    public Job patchJob(@PathVariable Integer id, @RequestBody Job jobDetails){
        return jobService.patchJob(id, jobDetails);
    }

}