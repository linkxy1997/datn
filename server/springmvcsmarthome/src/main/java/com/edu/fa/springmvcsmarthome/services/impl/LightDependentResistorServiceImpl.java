/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 6:49:29 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.LightDependentResistor;
import com.edu.fa.springmvcsmarthome.repositories.LightDependentResistorRepository;
import com.edu.fa.springmvcsmarthome.services.LightDependentResistorService;

@Service
public class LightDependentResistorServiceImpl implements LightDependentResistorService {
  @Autowired
  private LightDependentResistorRepository lightDependentResistorRepository;

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.LightDependentResistorService#save(com.
   * edu.fa.springmvchomeiot.entities.LightDependentResistor)
   */
  @Transactional
  @Override
  public boolean save(LightDependentResistor lightDependentResistor) {
    // TODO Auto-generated method stub
    lightDependentResistor.setTimeChange(new Date());
    return lightDependentResistorRepository.save(lightDependentResistor) != null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.edu.fa.springmvchomeiot.services.LightDependentResistorService#
   * getLastLightDependentResistor()
   */
  @Override
  public Optional<LightDependentResistor> getLastLightDependentResistor() {
    // TODO Auto-generated method stub
    return lightDependentResistorRepository.findTopByOrderByIdDesc();
  }

}
