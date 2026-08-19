export interface Skill {
  id: number;
  name: string;
}

export interface Company {
  companyId: number;
  name: string;
}

export interface Job {
  jobId: number;
  title: string;
  city: string;
  workModel: string;
  seniority: string;
  source: string;
  datePosted: string;
  link: string;
  company: Company;
  skills: Skill[];
}
