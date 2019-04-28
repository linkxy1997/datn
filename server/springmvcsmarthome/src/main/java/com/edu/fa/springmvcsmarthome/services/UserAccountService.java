/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:33:12 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services;

import java.util.Optional;

import com.edu.fa.springmvcsmarthome.entities.UserAccount;

public interface UserAccountService {
  public Optional<UserAccount> findByUsername(String username);
}
