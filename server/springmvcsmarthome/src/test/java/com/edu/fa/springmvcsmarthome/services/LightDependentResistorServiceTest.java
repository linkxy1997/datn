/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 4:25:44 PM
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

import com.edu.fa.springmvcsmarthome.entities.LightDependentResistor;
import com.edu.fa.springmvcsmarthome.repositories.LightDependentResistorRepository;
import com.edu.fa.springmvcsmarthome.services.impl.LightDependentResistorServiceImpl;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@RunWith(MockitoJUnitRunner.class)
public class LightDependentResistorServiceTest {
  @Mock
  private LightDependentResistorRepository lightRepository;
  @InjectMocks
  private LightDependentResistorServiceImpl lightService;
  LightDependentResistor lightResistor;
  LightDependentResistor lightResistorTest;
  Optional<LightDependentResistor> optional;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    lightResistor = new LightDependentResistor(1, 1, new Date());
    lightResistorTest = new LightDependentResistor(2, 0,
        new Date(Constants.EXPIRE_TIME));
    optional = Optional.of(lightResistor);
  }

  @Test
  public final void testSave() {
    when(lightRepository.save(lightResistor)).thenReturn(lightResistorTest);
    boolean flag = lightService.save(lightResistor);
    assertTrue("True", flag);
  }

  @Test
  public final void testSaveFalse() {
    when(lightRepository.save(lightResistor)).thenReturn(null);
    boolean flag = lightService.save(lightResistor);
    assertFalse("True", flag);
  }

  @Test
  public final void testGetLastLightDependentResistor() {
    when(lightRepository.findTopByOrderByIdDesc()).thenReturn(optional);
    Optional<LightDependentResistor> optional = lightService
        .getLastLightDependentResistor();
    assertEquals("True", optional, this.optional);
  }

  @Test
  public final void testGetLastLightDependentResistorNull() {
    when(lightRepository.findTopByOrderByIdDesc()).thenReturn(null);
    Optional<LightDependentResistor> optional = lightService
        .getLastLightDependentResistor();
    assertFalse("False", optional != null);
  }

}
