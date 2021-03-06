/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 9:03:28 AM Apr 6, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.Led;
import com.edu.fa.springmvcsmarthome.repositories.LedRepository;
import com.edu.fa.springmvcsmarthome.services.LedService;

@Service
public class LedServiceImpl implements LedService {

  @Autowired
  private LedRepository ledRepository;

  /*
   * (non-Javadoc)
   *
   * @see com.edu.fa.springmvchomeiot.services.LedService#save(com.edu.fa.
   * springmvchomeiot.entities.Led)
   */
  @Transactional
  @Override
  public boolean save(final Led led) {
    // Auto-generated method stub
    return ledRepository.save(led) != null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.edu.fa.springmvchomeiot.services.LedService#getCurrentLedStt()
   */
  @Override
  public Optional<Led> getCurrentLedStt() {
    // Auto-generated method stub
    return ledRepository.findTopByOrderByLedIdDesc();
  }

}
