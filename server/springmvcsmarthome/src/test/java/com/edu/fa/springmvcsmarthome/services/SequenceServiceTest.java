/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 5:00:17 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.edu.fa.springmvcsmarthome.entities.Sequence;
import com.edu.fa.springmvcsmarthome.repositories.SequenceRepository;
import com.edu.fa.springmvcsmarthome.repositories.impl.SequenceRepositoriesImpl;
import com.edu.fa.springmvcsmarthome.services.impl.SequenceServiceImpl;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@RunWith(MockitoJUnitRunner.class)
public class SequenceServiceTest {
  @Mock
  private SequenceRepositoriesImpl sequenceRepositories;
  @Mock
  private SequenceRepository sequenceRepository;
  @InjectMocks
  private SequenceServiceImpl sequenceService;
  Sequence sequence;
  Sequence sequenceTest;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    sequence = new Sequence();
    sequence.setId(Constants.AIR_CONDITIONER);
    sequence.setSeq(1);
    sequenceTest = new Sequence(Constants.AIR_CONDITIONER, 2);
  }

  @Test
  public final void testGetNextSequenceId() {
    when(sequenceRepositories.getNextSequenceId(Mockito.anyString()))
        .thenReturn(sequence);
    int seqId = sequenceService.getNextSequenceId("Key");
    assertEquals("True", 1, seqId);
  }

  @Test
  public final void testGetNextSequenceIdWhenNull() {
    when(sequenceRepositories.getNextSequenceId(Mockito.anyString()))
        .thenReturn(null);

    when(sequenceRepository.save(Mockito.any(Sequence.class)))
        .thenReturn(sequenceTest);
    int seqId = sequenceService.getNextSequenceId("key");
    assertEquals("True", 0, seqId);
  }
}
