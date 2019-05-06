/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 1, 2019 9:56:43 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edu.fa.springmvcsmarthome.entities.Role;
import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.repositories.UserAccountRepository;
import com.edu.fa.springmvcsmarthome.services.impl.UserAccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceTest {
  @Mock
  private UserAccountRepository userAccountRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private UserAccountServiceImpl userAccountService;
  Optional<UserAccount> optional;
  UserAccount userAccount;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    optional = Optional.ofNullable(this.userAccount);
  }

  @Test
  public void testFindByUsername() {
    List<Role> roles = new ArrayList<>();
    roles.add(new Role(1, "ROLE_ADMIN"));
    UserAccount userAccount = new UserAccount(1, "admin", "123456", roles);
    UserAccount userAccount1 = new UserAccount(2, "TungNM13", "password",
        roles);
    Optional<UserAccount> optional = Optional.of(userAccount);
    when(userAccountRepository.findByUsername(Mockito.anyString()))
        .thenReturn(optional);
    when(passwordEncoder.matches("password", "123456")).thenReturn(true);
    assertEquals("True", true, userAccountService.findByUsername(userAccount1));
    assertFalse("False", userAccountService.findByUsername(userAccount));
  }

  @Test
  public void testFindByUsernameOverload() {
    List<Role> roles = new ArrayList<>();
    roles.add(new Role(1, "ROLE_ADMIN"));
    UserAccount userAccount = new UserAccount(1, "admin", "123456", roles);
    Optional<UserAccount> optional = Optional.of(userAccount);
    when(userAccountRepository.findByUsername(Mockito.anyString()))
        .thenReturn(optional);
    UserAccount account = userAccountService.findByUsername("tungnm13").get();
    assertEquals("True", account, userAccount);
    String roleName = account.getRoles().get(0).getRoleName();
    assertFalse("False", roleName.equals("ROLE_USER"));
  }

  @Test
  public void testFindByUsernameNull() {
    when(userAccountRepository.findByUsername(Mockito.anyString()))
        .thenReturn(this.optional);
    List<Role> roles = new ArrayList<>();
    roles.add(new Role(1, "ROLE_ADMIN"));
    UserAccount userAccount = new UserAccount(1, "admin", "123456", roles);
    boolean flag = userAccountService.findByUsername(userAccount);
    assertEquals("false", false, flag);
  }
}
