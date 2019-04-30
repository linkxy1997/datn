/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 28, 2019 3:41:34 PM
 * @version 1.0
 */
/**
 * 
 */
package com.edu.fa.springmvcsmarthome.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.AutomationState;

/**
 * Extension of {@link MongoRepository} to provide additional methods to
 * retrieve entities using the pagination and sorting abstraction.
 * 
 * @author linkx
 */
public interface AutomationStateRepository
    extends MongoRepository<AutomationState, Integer> {

}
