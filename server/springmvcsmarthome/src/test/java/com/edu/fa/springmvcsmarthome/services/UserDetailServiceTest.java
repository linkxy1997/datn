/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 1, 2019 9:26:40 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.edu.fa.springmvcsmarthome.entities.Role;
import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.repositories.UserAccountRepository;
import com.edu.fa.springmvcsmarthome.services.impl.UserDetailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailServiceTest {
  @Mock
  private UserAccountRepository userAccountRepository;
  @InjectMocks
  private UserDetailServiceImpl userDetailService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public final void testLoadUserByUsername() {
    List<Role> roles = new ArrayList<>();
    roles.add(new Role(1, "ROLE_ADMIN"));
    UserAccount userAccount = new UserAccount(1, "admin", "123456", roles);
    Optional<UserAccount> optional = Optional.of(userAccount);
    when(userAccountRepository.findByUsername(Mockito.anyString()))
        .thenReturn(optional);
    UserDetails userDetails = userDetailService.loadUserByUsername("123");
    assertEquals("True", userDetails.getPassword(),
        optional.get().getPassword());
    assertEquals("True", userDetails.getUsername(),
        optional.get().getUsername());
    assertEquals("True", false, userDetails.getAuthorities().isEmpty());
  }

  @Test
  public final void testLoadUserByUsernameFasle() {
    List<Role> roles = new ArrayList<>();
    roles.add(new Role(1, "ROLE_ADMIN"));
    UserAccount userAccount = new UserAccount(1, "admin", "123456", roles);
    Optional<UserAccount> optional = Optional.of(userAccount);
    when(userAccountRepository.findByUsername("admin1")).thenReturn(optional);
    UserDetails userDetails = userDetailService.loadUserByUsername("admin1");
    String username = userDetails.getUsername();
    assertFalse("False", username.equals("admin1"));
  }

  @Test(expected = UsernameNotFoundException.class)
  public final void testLoadUserByUsernameException() {

    when(userDetailService.loadUserByUsername(Mockito.anyString()))
        .thenThrow(UsernameNotFoundException.class);
    userDetailService.loadUserByUsername("TungNM13");
  }

  @Test(expected = NullPointerException.class)
  public final void testLoadUserByUsernameNullPointerException() {
    userDetailService = mock(UserDetailServiceImpl.class);
    when(userDetailService.loadUserByUsername(Mockito.anyString()))
        .thenThrow(NullPointerException.class);
    userDetailService.loadUserByUsername("TungNM13");
  }
}
