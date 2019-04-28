/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 6:40:46 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.LightDependentResistor;

public interface LightDependentResistorService {
  public abstract boolean save(LightDependentResistor lightDependentResistor);

  public Optional<LightDependentResistor> getLastLightDependentResistor();
}
