import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Skill, NewSkill } from '../models/skill.model';

@Injectable({ providedIn: 'root' })
export class SkillService {
  private baseUrl = 'http://localhost:8080/skills';

  constructor(private http: HttpClient) {}

  getAllSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.baseUrl);
  }

  createSkill(skill: NewSkill): Observable<Skill>{
    return this.http.post<Skill>(this.baseUrl, skill);
  }
}
