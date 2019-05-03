/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 30, 2019 11:12:45 PM
 * @version 1.0
 */
/**
 * 
 */
package com.edu.fa.springmvcsmarthome.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.fa.springmvcsmarthome.entities.Role;
import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.repositories.UserAccountRepository;

/**
 * @author linkx
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  private UserAccountRepository userAccountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    // Auto-generated method stub
    Optional<UserAccount> optional = userAccountRepository
        .findByUsername(username);
    if (optional.isPresent()) {
      UserAccount userAccount = optional.get();
      boolean enabled = true;
      boolean accountNonExpired = true;
      boolean credentialsNonExpired = true;
      boolean accountNonLocked = true;
      List<GrantedAuthority> authorities = new ArrayList<>();
      for (Role role : userAccount.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
      }
      return new User(userAccount.getUsername(), userAccount.getPassword(),
          enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
          authorities);
    } else {
      throw new UsernameNotFoundException(username);
    }
  }
}
