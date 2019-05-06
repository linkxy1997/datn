/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 9:07:42 AM Apr 6, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.fa.springmvcsmarthome.entities.Sequence;
import com.edu.fa.springmvcsmarthome.repositories.SequenceRepositories;
import com.edu.fa.springmvcsmarthome.repositories.SequenceRepository;
import com.edu.fa.springmvcsmarthome.services.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {
  @Autowired
  private SequenceRepository sequenceRepository;
  @Autowired
  private SequenceRepositories sequenceRepositories;

  /*
   * (non-Javadoc)
   *
   * @see
   * com.edu.fa.springmvchomeiot.services.SequenceService#getNextSequenceId(
   * java. lang.String)
   */
  @Override
  @Transactional
  public int getNextSequenceId(String key) {
    // Auto-generated method stub
    Sequence seqId = sequenceRepositories.getNextSequenceId(key);
    if (null == seqId) {
      Sequence sequence = new Sequence();
      sequence.setId(key);
      sequence.setSeq(0);
      sequenceRepository.save(sequence);
      return sequence.getSeq();
    }
    return seqId.getSeq();
  }
}
