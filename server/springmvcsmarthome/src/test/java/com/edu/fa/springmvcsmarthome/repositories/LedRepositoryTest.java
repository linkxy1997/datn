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

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.fa.springmvcsmarthome.config.MongoConfig;
import com.edu.fa.springmvcsmarthome.entities.Led;

@DataMongoTest
@ContextConfiguration(classes = MongoConfig.class)
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class LedRepositoryTest {
  @Autowired
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
    List<Led> leds = ledRepository.findAll();
    assertEquals("True", leds.size(), 238);
  }

  @Test
  public void testFindAllFalse() {
    List<Led> leds = ledRepository.findAll();
    assertFalse("False", leds.size() == 100);
  }


}
