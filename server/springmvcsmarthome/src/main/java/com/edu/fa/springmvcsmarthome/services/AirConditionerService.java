/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 25, 2019 4:09:14 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.AirConditioner;

public interface AirConditionerService {
  public abstract boolean save(AirConditioner airConditioner);

  public abstract Optional<AirConditioner> findTopByOrderByIdDesc();
}
