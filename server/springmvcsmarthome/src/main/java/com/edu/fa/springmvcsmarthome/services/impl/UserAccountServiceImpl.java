/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:34:29 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.repositories.UserAccountRepository;
import com.edu.fa.springmvcsmarthome.services.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
  @Autowired
  private UserAccountRepository userAccountRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.UserAccountService#findByUsername(
   * java. lang.String)
   */
  @Override
  public Optional<UserAccount> findByUsername(String username) {
    // TODO Auto-generated method stub
    return userAccountRepository.findByUsername(username);
  }

  @Override
  public boolean findByUsername(UserAccount userAccount) {
    // TODO Auto-generated method stub
    Optional<UserAccount> optional = userAccountRepository
        .findByUsername(userAccount.getUsername());
    if (optional.isPresent()) {
      String rawPassword = userAccount.getPassword();
      boolean flag = passwordEncoder.matches(rawPassword,
          optional.get().getPassword());
      return flag;
    }
    return false;
  }

}
