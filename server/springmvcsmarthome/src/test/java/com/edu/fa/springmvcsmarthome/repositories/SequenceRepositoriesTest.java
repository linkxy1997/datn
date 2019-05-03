/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 4:40:19 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.edu.fa.springmvcsmarthome.entities.Sequence;
import com.edu.fa.springmvcsmarthome.repositories.impl.SequenceRepositoriesImpl;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@RunWith(MockitoJUnitRunner.class)
public class SequenceRepositoriesTest {
  @Mock
  private MongoTemplate mongoTemplate;
  @InjectMocks
  private SequenceRepositoriesImpl sequenceRepositories;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public final void testGetNextSequenceId() {
    Sequence sequence = new Sequence();
    sequence.setId(Constants.AIR_CONDITIONER);
    sequence.setSeq(1);
    when(mongoTemplate.findAndModify(Mockito.any(Query.class),
        Mockito.any(Update.class), Mockito.any(FindAndModifyOptions.class),
        Mockito.any())).thenReturn(sequence);
    Sequence seq = sequenceRepositories
        .getNextSequenceId(Constants.AIR_CONDITIONER);
    assertEquals("True", seq, sequence);
  }

  @Test
  public final void testGetNextSequenceIdFalse() {
    when(mongoTemplate.findAndModify(Mockito.any(Query.class),
        Mockito.any(Update.class), Mockito.any(FindAndModifyOptions.class),
        Mockito.any())).thenReturn(null);
    Sequence seq = sequenceRepositories
        .getNextSequenceId(Constants.AIR_CONDITIONER);
    assertFalse("False", null != seq);
  }
}
