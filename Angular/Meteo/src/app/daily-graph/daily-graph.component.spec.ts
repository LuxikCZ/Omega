import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyGraphComponent } from './daily-graph.component';

describe('DailyGraphComponent', () => {
  let component: DailyGraphComponent;
  let fixture: ComponentFixture<DailyGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DailyGraphComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
