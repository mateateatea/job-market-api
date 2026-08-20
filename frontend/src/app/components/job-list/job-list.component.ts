import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobService } from '../../services/job.service';
import { Job } from '../../models/job.model';
import { AddJobFormComponent } from '../add-job-form/add-job-form';

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [CommonModule, AddJobFormComponent],
  templateUrl: './job-list.component.html',
  styleUrl: './job-list.component.css',
})
export class JobListComponent implements OnInit {
  jobs = signal<Job[]>([]);

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.jobService.getAllJobs().subscribe({
      next: (data) => {
        console.log('Jobs received:', data);
        this.jobs.set(data);
      },
      error: (err) => console.error('Failed to load jobs', err),
    });
  }

  onJobAdded(): void {
    this.loadJobs();
  }
}
