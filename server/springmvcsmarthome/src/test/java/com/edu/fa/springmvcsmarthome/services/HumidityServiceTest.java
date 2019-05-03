/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 27, 2019 8:17:21 PM
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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.edu.fa.springmvcsmarthome.entities.Humidity;
import com.edu.fa.springmvcsmarthome.repositories.HumidityRepository;
import com.edu.fa.springmvcsmarthome.services.impl.HumidityServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HumidityServiceTest {
  @Mock
  private HumidityRepository humidityRepository;
  @InjectMocks
  private HumidityServiceImpl humidityService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSaveTrue() {
    Humidity humidity = new Humidity(1, 80, new Date());
    when(humidityRepository.save(humidity)).thenReturn(humidity);
    assertEquals("True", true, humidityService.save(humidity));
  }

  @Test
  public void testSaveFalse() {
    Humidity humidity = new Humidity(1, 80, new Date());
    when(humidityRepository.save(humidity)).thenReturn(null);
    assertEquals("True", false, humidityService.save(humidity));
  }

  @Test
  public void testGetLastHumidityTrue() {
    Humidity humidity = new Humidity(1, 80, new Date());
    Optional<Humidity> value = Optional.of(humidity);
    when(humidityRepository.findTopByOrderByHumidityIdDesc()).thenReturn(value);
    Optional<Humidity> optional = humidityService.getLastHumidity();
    assertEquals("True", value.get(), optional.get());
  }

  @Test
  public void testGetLastHumidityFalse() {
    Humidity humidity = new Humidity(1, 80, new Date());
    Humidity humidity2 = new Humidity(2, 80, new Date());
    Optional<Humidity> value = Optional.of(humidity);
    Optional<Humidity> value2 = Optional.of(humidity2);
    when(humidityRepository.findTopByOrderByHumidityIdDesc()).thenReturn(value);
    Optional<Humidity> optional = humidityService.getLastHumidity();
    assertFalse("False", optional.get().equals(value2.get()));
  }
}
