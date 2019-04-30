/*
 * (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 8:53:54 AM Apr 7, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.Humidity;

/**
 * Extension of {@link MongoRepository} to provide additional methods to
 * retrieve entities using the pagination and sorting abstraction.
 * 
 * @author linkx
 */
public interface HumidityRepository extends MongoRepository<Humidity, Integer> {
  /**
   * TODO FindTopByOrderByHumidityIdDesc.
   * 
   * @return {@link Optional} Humidity.
   */
  Optional<Humidity> findTopByOrderByHumidityIdDesc();
}
