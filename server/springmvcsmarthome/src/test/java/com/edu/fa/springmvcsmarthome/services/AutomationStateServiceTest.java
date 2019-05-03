/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 7:12:45 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

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

import com.edu.fa.springmvcsmarthome.entities.AutomationState;
import com.edu.fa.springmvcsmarthome.repositories.AutomationStateRepository;
import com.edu.fa.springmvcsmarthome.services.impl.AutomationStateServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AutomationStateServiceTest {
  @InjectMocks
  private AutomationStateServiceImpl automationService;
  @Mock
  private AutomationStateRepository automationRepository;
  AutomationState automationState;
  Optional<AutomationState> optional;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    automationState = new AutomationState(1, 1);
    optional = Optional.of(automationState);
  }

  @Test
  public final void testSaveOrUpdate() {
    when(automationRepository.save(automationState))
        .thenReturn(automationState);
    boolean flag = automationService.saveOrUpdate(automationState);
    assertEquals("True", true, flag);
  }

  @Test
  public final void testSaveOrUpdateFalse() {
    when(automationRepository.save(automationState)).thenReturn(null);
    boolean flag = automationService.saveOrUpdate(automationState);
    assertEquals("True", false, flag);
  }

  @Test
  public final void testFindOne() {
    when(automationRepository.findById(Mockito.anyInt())).thenReturn(optional);
    Optional<AutomationState> optional = automationService.findOne(2);
    assertEquals("True", optional, this.optional);
  }

  @Test
  public final void testFindOneNull() {
    this.optional = Optional.ofNullable(null);
    when(automationRepository.findById(Mockito.anyInt())).thenReturn(optional);
    Optional<AutomationState> optional = automationService.findOne(2);
    assertFalse("False", optional.isPresent());
  }

}
