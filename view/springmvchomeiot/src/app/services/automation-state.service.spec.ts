import { TestBed } from '@angular/core/testing';

import { AutomationStateService } from './automation-state.service';

describe('AutomationStateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AutomationStateService = TestBed.get(AutomationStateService);
    expect(service).toBeTruthy();
  });
});
