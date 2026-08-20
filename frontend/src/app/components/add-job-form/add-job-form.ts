import { Component, EventEmitter, Output, OnInit, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { JobService } from '../../services/job.service';
import { CompanyService } from '../../services/company.service';
import { SkillService } from '../../services/skill.service';
import { Company, Skill, NewJob } from '../../models/job.model';

@Component({
  selector: 'app-add-job-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-job-form.html',
  styleUrl: './add-job-form.css',
})
export class AddJobFormComponent implements OnInit {
  @Output() jobAdded = new EventEmitter<void>();

  companies = signal<Company[]>([]);
  skills = signal<Skill[]>([]);

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private jobService: JobService,
    private companyService: CompanyService,
    private skillService: SkillService,
  ) {
    this.form = this.fb.group({
      title: ['', Validators.required],
      companyId: [null, Validators.required],
      city: [''],
      workModel: ['remote'],
      seniority: ['junior'],
      source: [''],
      datePosted: [''],
      link: [''],
      skillIds: [[] as number[]],
    });
  }

  ngOnInit(): void {
    this.companyService.getAllCompanies().subscribe((c) => this.companies.set(c));
    this.skillService.getAllSkills().subscribe((s) => this.skills.set(s));
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const v = this.form.value;
    const payload: NewJob = {
      title: v.title,
      company: { companyId: v.companyId },
      city: v.city,
      workModel: v.workModel,
      seniority: v.seniority,
      source: v.source,
      datePosted: v.datePosted,
      link: v.link,
      skills: (v.skillIds as number[]).map((id) => ({ id })),
    };

    this.jobService.createJob(payload).subscribe(() => {
      this.form.reset({ workModel: 'remote', seniority: 'junior', skillIds: [] });
      this.jobAdded.emit();
    });
  }
  toggleSkill(id: number, event: Event): void {
    const checked = (event.target as HTMLInputElement).checked;
    const current: number[] = this.form.value.skillIds ?? [];
    const updated = checked ? [...current, id] : current.filter((sid) => sid !== id);
    this.form.patchValue({ skillIds: updated });
  }
}
