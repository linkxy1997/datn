/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 28, 2019 3:44:06 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.fa.springmvcsmarthome.entities.Automation;
import com.edu.fa.springmvcsmarthome.repositories.AutomationStateRepository;
import com.edu.fa.springmvcsmarthome.services.AutomationStateService;

@Service
public class AutomationStateServiceImpl implements AutomationStateService {
  @Autowired
  private AutomationStateRepository automationStateRepository;

  @Override
  public boolean saveOrUpdate(Automation automation) {
    // Auto-generated method stub
    return automationStateRepository.save(automation) != null;
  }

  @Override
  public Optional<Automation> findOne(int id) {
    // Auto-generated method stub
    return automationStateRepository.findById(id);
  }

}
