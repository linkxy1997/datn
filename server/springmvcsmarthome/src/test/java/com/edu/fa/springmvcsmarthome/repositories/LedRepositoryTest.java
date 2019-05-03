/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 27, 2019 11:10:32 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.edu.fa.springmvcsmarthome.config.MongoConfig;
import com.edu.fa.springmvcsmarthome.entities.Led;

@DataMongoTest
@ContextConfiguration(classes = MongoConfig.class)
@Rollback
@RunWith(MockitoJUnitRunner.class)
public class LedRepositoryTest {
  @Mock
  private LedRepository ledRepository;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAllTrue() {
    List<Led> leds = new ArrayList<>();
    leds.add(new Led());
    when(ledRepository.findAll()).thenReturn(leds);
    List<Led> ledTest = ledRepository.findAll();
    verify(ledRepository).findAll();
    assertEquals("True", ledTest, leds);
  }

  @Test
  public void testFindAllFalse() {
    List<Led> leds = ledRepository.findAll();
    assertFalse("False", leds.size() == 100);
  }

}
