import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardSeriesComponent } from './dashboard-series.component';

describe('DashboardSeriesComponent', () => {
  let component: DashboardSeriesComponent;
  let fixture: ComponentFixture<DashboardSeriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardSeriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardSeriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
