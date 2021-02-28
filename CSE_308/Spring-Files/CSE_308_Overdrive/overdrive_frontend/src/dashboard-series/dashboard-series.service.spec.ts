import { TestBed, inject } from '@angular/core/testing';

import { DashboardSeriesService } from './dashboard-series.service';

describe('DashboardSeriesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DashboardSeriesService]
    });
  });

  it('should be created', inject([DashboardSeriesService], (service: DashboardSeriesService) => {
    expect(service).toBeTruthy();
  }));
});
