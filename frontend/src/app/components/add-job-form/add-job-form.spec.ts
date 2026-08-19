import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddJobForm } from './add-job-form';

describe('AddJobForm', () => {
  let component: AddJobForm;
  let fixture: ComponentFixture<AddJobForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddJobForm],
    }).compileComponents();

    fixture = TestBed.createComponent(AddJobForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
