/*
 * (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 8:54:57 AM Apr 7, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.Temperature;

public interface TemperatureRepository extends MongoRepository<Temperature, Integer> {
  public abstract Optional<Temperature> findTopByOrderByTemperatureIdDesc();
}
