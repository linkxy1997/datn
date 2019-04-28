/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 25, 2019 4:09:24 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.AirConditioner;
import com.edu.fa.springmvcsmarthome.repositories.AirConditionerRepository;
import com.edu.fa.springmvcsmarthome.services.AirConditionerService;

@Service
public class AirConditionerServiceImpl implements AirConditionerService {
  @Autowired
  private AirConditionerRepository airConditionerRepository;

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.AirConditionerService#save(com.edu.fa.
   * springmvchomeiot.entities.AirConditioner)
   */
  @Override
  @Transactional
  public boolean save(final AirConditioner airConditioner) {
    // TODO Auto-generated method stub
    return airConditionerRepository.save(airConditioner) != null;
  }

  @Override
  public Optional<AirConditioner> findTopByOrderByIdDesc() {
    // TODO Auto-generated method stub
    return airConditionerRepository.findTopByOrderByIdDesc();
  }

}
