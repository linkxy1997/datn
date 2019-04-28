/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 22, 2019 9:39:35 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.RainWaterSensor;

public interface RainWaterSensorRepostitory extends MongoRepository<RainWaterSensor, Integer> {
  public abstract Optional<RainWaterSensor> findTopByOrderByRainIdDesc();
}
