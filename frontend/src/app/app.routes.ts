import { Routes } from '@angular/router';
import { SkillList } from './components/skill-list/skill-list';
import { JobListComponent } from './components/job-list/job-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/jobs', pathMatch: 'full' },
  { path: 'jobs', component: JobListComponent },
  { path: 'skills', component: SkillList },
];

