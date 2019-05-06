/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 5, 2019 9:33:36 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.*;
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

import com.edu.fa.springmvcsmarthome.entities.Temperature;
import com.edu.fa.springmvcsmarthome.repositories.TemperatureRepository;
import com.edu.fa.springmvcsmarthome.services.impl.TemperatureServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceTest {
  @Mock
  private TemperatureRepository tempRepository;
  @InjectMocks
  private TemperatureServiceImpl tempService;
  Temperature temp;
  Optional<Temperature> optional;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    temp = new Temperature(1, 14, new Date());
    Optional.of(temp);
  }

  @Test
  public void testSaveTemperature() {
    when(tempRepository.save(temp)).thenReturn(temp);
    boolean flag = tempService.saveTemperature(temp);
    assertEquals("True", true, flag);
  }

  @Test
  public void testSaveTemperatureFalse() {
    when(tempRepository.save(temp)).thenReturn(null);
    boolean flag = tempService.saveTemperature(temp);
    assertEquals("False", false, flag);
  }

  @Test
  public void testGetLastTemperature() {
    when(tempRepository.findTopByOrderByTemperatureIdDesc())
        .thenReturn(optional);
    Optional<Temperature> optional = tempService.getLastTemperature();
    assertEquals("True", optional, this.optional);
  }

}
