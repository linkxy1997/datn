/*
 * (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 9:00:55 AM Apr 6, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.Temperature;

public interface TemperatureService {
  /**
   * save new Temperature to Database.
   *
   * @param temperature : Temperature to save.
   * @return
   */
  public abstract boolean saveTemperature(Temperature temperature);

  public abstract Optional<Temperature> getLastTemperature();
}
