/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 6:42:44 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.RainWaterSensor;
import com.edu.fa.springmvcsmarthome.repositories.RainWaterSensorRepostitory;
import com.edu.fa.springmvcsmarthome.services.RainWaterSensorService;

@Service
public class RainWaterSensorServiceImpl implements RainWaterSensorService {
  @Autowired
  private RainWaterSensorRepostitory rainWaterSensorRepostitory;

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.RainWaterSensorService#save(com.edu.fa.
   * springmvchomeiot.entities.RainWaterSensor)
   */
  @Transactional
  @Override
  public boolean save(RainWaterSensor rainWaterSensor) {
    // TODO Auto-generated method stub
    return rainWaterSensorRepostitory.save(rainWaterSensor) != null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.edu.fa.springmvchomeiot.services.RainWaterSensorService#
   * getLastRainWaterSensor()
   */
  @Override
  public Optional<RainWaterSensor> getLastRainWaterSensor() {
    // TODO Auto-generated method stub
    return rainWaterSensorRepostitory.findTopByOrderByRainIdDesc();
  }

}
