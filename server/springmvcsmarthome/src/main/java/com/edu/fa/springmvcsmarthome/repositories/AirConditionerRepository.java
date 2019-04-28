/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 25, 2019 4:08:46 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.AirConditioner;

public interface AirConditionerRepository extends MongoRepository<AirConditioner, Integer> {
  public abstract Optional<AirConditioner> findTopByOrderByIdDesc();
}
