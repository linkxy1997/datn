/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 7:02:11 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.Humidity;
import com.edu.fa.springmvcsmarthome.repositories.HumidityRepository;
import com.edu.fa.springmvcsmarthome.services.HumidityService;

@Service
public class HumidityServiceImpl implements HumidityService {
  @Autowired
  private HumidityRepository humidityRepository;

  /*
   * (non-Javadoc)
   *
   * @see com.edu.fa.springmvchomeiot.services.HumidityService#save(com.edu.fa.
   * springmvchomeiot.entities.Humidity)
   */
  @Override
  @Transactional
  public boolean save(Humidity humidity) {
    // Auto-generated method stub
    humidity.setTimeAdd(new Date());
    return humidityRepository.save(humidity) != null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.edu.fa.springmvchomeiot.services.HumidityService#getLastHumidity()
   */
  @Override
  public Optional<Humidity> getLastHumidity() {
    // Auto-generated method stub
    return humidityRepository.findTopByOrderByHumidityIdDesc();
  }

}
