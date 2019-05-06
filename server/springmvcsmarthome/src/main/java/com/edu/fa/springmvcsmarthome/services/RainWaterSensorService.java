/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 6:41:02 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.RainWaterSensor;

public interface RainWaterSensorService {
  /**
   * save new RainWaterSensor.
   *
   * @param rainWaterSensor
   * @return
   */
  public abstract boolean save(RainWaterSensor rainWaterSensor);

  public Optional<RainWaterSensor> getLastRainWaterSensor();
}
