import { Component, OnInit, inject, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import {SkillService} from '../../services/skill.service';
import { NewSkill, Skill } from '../../models/skill.model';

@Component({
  selector: 'app-skill-list',
  imports: [ReactiveFormsModule],
  templateUrl: './skill-list.html',
  styleUrl: './skill-list.css',
})
export class SkillList implements OnInit {
  private SkillService = inject(SkillService);

  skills = signal<Skill[]>([]);

  ngOnInit() {
    this.SkillService.getAllSkills().subscribe({
      next: (data) => {
        this.skills.set(data);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      name: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const v: any = this.form.value;
    const payload: NewSkill = {
      name: v.name,
    };

    this.SkillService.createSkill(payload).subscribe(() => {
      this.form.reset();
      this.ngOnInit();
    });
  }
}
