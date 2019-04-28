import { TestBed } from '@angular/core/testing';

import { AirConditionerService } from './air-conditioner.service';

describe('AirConditionerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AirConditionerService = TestBed.get(AirConditionerService);
    expect(service).toBeTruthy();
  });
});
