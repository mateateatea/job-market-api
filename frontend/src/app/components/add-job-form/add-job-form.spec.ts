import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddJobFormComponent } from './add-job-form';

describe('AddJobFormComponent', () => {
  let component: AddJobFormComponent;
  let fixture: ComponentFixture<AddJobFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddJobFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AddJobFormComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
