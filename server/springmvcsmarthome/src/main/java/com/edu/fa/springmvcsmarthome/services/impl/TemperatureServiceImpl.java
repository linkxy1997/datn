/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 3:14:34 PM Apr 7, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.Temperature;
import com.edu.fa.springmvcsmarthome.repositories.TemperatureRepository;
import com.edu.fa.springmvcsmarthome.services.TemperatureService;

@Service
public class TemperatureServiceImpl implements TemperatureService {
  @Autowired
  private TemperatureRepository temperatureRepository;

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.TemperatureService#saveTemperature(
   * com. edu.fa.springmvchomeiot.entities.Temperature)
   */
  @Transactional
  @Override
  public boolean saveTemperature(Temperature temperature) {
    // TODO Auto-generated method stub
    return temperatureRepository.save(temperature) != null;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.TemperatureService#getLastTemperature(
   * )
   */
  @Override
  public Optional<Temperature> getLastTemperature() {
    // Auto-generated method stub
    return temperatureRepository.findTopByOrderByTemperatureIdDesc();
  }

}
