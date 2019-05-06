/*
 * (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 8:54:57 AM Apr 7, 2019
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.Led;

/**
 * interface LedService.
 *
 * @author linkx
 *
 */
public interface LedService {
  /**
   * Save new led to Database.
   *
   * @param led
   *
   */
  public boolean save(Led led);

  /**
   * Get last index of Led status.
   *
   * @return
   */
  public Optional<Led> getCurrentLedStt();
}
