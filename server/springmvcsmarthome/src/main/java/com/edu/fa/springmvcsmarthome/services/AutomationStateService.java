/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 28, 2019 3:42:26 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.Automation;

public interface AutomationStateService {
  public abstract boolean saveOrUpdate(Automation automation);

  public Optional<Automation> findOne(int id);
}
