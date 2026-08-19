package com.mati.job_market_api.service;

import com.mati.job_market_api.repository.JobRepository;
import com.mati.job_market_api.exception.ResourceNotFoundException;
import com.mati.job_market_api.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Job getJobById(Integer id){
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job updateJob(Integer id, Job jobDetails) {
        Job job = getJobById(id);
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

    public Job patchJob(Integer id, Job jobDetails) {
        Job job = getJobById(id);
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

    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }
}
