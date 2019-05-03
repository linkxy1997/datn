/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 1:30:58 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edu.fa.springmvcsmarthome.entities.Led;
import com.edu.fa.springmvcsmarthome.repositories.LedRepository;
import com.edu.fa.springmvcsmarthome.services.impl.LedServiceImpl;
import com.edu.fa.springmvcsmarthome.utils.Constants;

public class LedServiceTest {
  @Mock
  private LedRepository ledRepository;
  @InjectMocks
  private LedServiceImpl ledService;
  Led led;
  Led ledTest;
  Optional<Led> optional;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    led = new Led(1, 1, new Date());
    ledTest = new Led(2, 0, new Date(Constants.EXPIRE_TIME));
    optional = Optional.of(led);
  }

  @Test
  public final void testSave() {
    when(ledRepository.save(led)).thenReturn(ledTest);
    boolean flag = ledService.save(led);
    assertEquals("True", true, flag);
  }

  @Test
  public final void testGetCurrentLedStt() {
    when(ledRepository.findTopByOrderByLedIdDesc()).thenReturn(optional);
    Optional<Led> optionalLed = ledService.getCurrentLedStt();
    led = optional.get();
    ledTest = optionalLed.get();
    assertEquals("True", optionalLed, optional);

    assertFalse("False", led.getLedId() == 2);
  }

}
