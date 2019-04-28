/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 7:01:27 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.Humidity;

public interface HumidityService {
  public abstract boolean save(Humidity humidity);

  public abstract Optional<Humidity> getLastHumidity();
}
