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

import com.edu.fa.springmvcsmarthome.entities.AutomationState;
import com.edu.fa.springmvcsmarthome.repositories.AutomationStateRepository;
import com.edu.fa.springmvcsmarthome.services.AutomationStateService;

@Service
public class AutomationStateServiceImpl implements AutomationStateService {
  @Autowired
  private AutomationStateRepository automationStateRepository;

  @Override
  public boolean saveOrUpdate(AutomationState automationState) {
    // TODO Auto-generated method stub
    return automationStateRepository.save(automationState) != null;
  }

  @Override
  public Optional<AutomationState> findOne(int id) {
    // TODO Auto-generated method stub
    Optional<AutomationState> optional = automationStateRepository.findById(id);
    return optional;
  }

}
