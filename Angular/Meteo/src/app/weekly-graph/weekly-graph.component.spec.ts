import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeeklyGraphComponent } from './weekly-graph.component';

describe('WeeklyGraphComponent', () => {
  let component: WeeklyGraphComponent;
  let fixture: ComponentFixture<WeeklyGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeeklyGraphComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeeklyGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
