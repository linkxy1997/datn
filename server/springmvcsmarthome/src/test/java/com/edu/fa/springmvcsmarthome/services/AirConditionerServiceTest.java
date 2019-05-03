/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 1:46:24 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.edu.fa.springmvcsmarthome.entities.AirConditioner;
import com.edu.fa.springmvcsmarthome.repositories.AirConditionerRepository;
import com.edu.fa.springmvcsmarthome.services.impl.AirConditionerServiceImpl;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@RunWith(MockitoJUnitRunner.class)
public class AirConditionerServiceTest {
  @Mock
  private AirConditionerRepository airRepository;
  @InjectMocks
  private AirConditionerServiceImpl airService;
  AirConditioner air;
  AirConditioner airTest;
  Optional<AirConditioner> optional;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    air = new AirConditioner(1, 1, new Date());
    airTest = new AirConditioner(0, 0, new Date(Constants.EXPIRE_TIME));
    optional = Optional.of(air);
  }

  @Test
  public final void testSave() {
    when(airRepository.save(air)).thenReturn(airTest);
    boolean flag = airService.save(air);
    assertTrue("True", flag);
  }

  @Test
  public final void testFindTopByOrderByIdDesc() {
    when(airRepository.findTopByOrderByIdDesc()).thenReturn(optional);
    Optional<AirConditioner> optionalAir = airService.findTopByOrderByIdDesc();
    assertEquals("True", optional, optionalAir);
    assertFalse("False", optionalAir.get().getTimeChange().after(new Date()));
  }

}
