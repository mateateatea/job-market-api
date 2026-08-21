import { Component, OnInit, inject, signal } from '@angular/core';
import {SkillService} from '../../services/skill.service';
import {Skill} from  '../../models/skill.model';

@Component({
  selector: 'app-skill-list',
  imports: [],
  templateUrl: './skill-list.html',
  styleUrl: './skill-list.css',
})
export class SkillList implements OnInit {
  private SkillService = inject(SkillService);

  skills = signal<Skill[]>([]);

  ngOnInit() {
    this.SkillService.getAllSkills().subscribe({
      next: data => {
        this.skills.set(data);
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
