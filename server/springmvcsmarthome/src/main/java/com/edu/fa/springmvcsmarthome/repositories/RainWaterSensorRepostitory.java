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
/**
 * Extension of {@link MongoRepository} to provide additional methods to
 * retrieve entities using the pagination and sorting abstraction.
 * 
 * @author linkx
 */
public interface RainWaterSensorRepostitory
    extends MongoRepository<RainWaterSensor, Integer> {
  Optional<RainWaterSensor> findTopByOrderByRainIdDesc();
}
