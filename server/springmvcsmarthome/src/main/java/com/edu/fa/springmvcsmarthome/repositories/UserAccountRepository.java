/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:30:09 PM
 * @version 1.0
 */
/**
 *
 */

package com.edu.fa.springmvcsmarthome.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.UserAccount;

/**
 * @author linkx
 *
 */
public interface UserAccountRepository
    extends MongoRepository<UserAccount, Integer> {
  public abstract Optional<UserAccount> findByUsername(String username);
}
