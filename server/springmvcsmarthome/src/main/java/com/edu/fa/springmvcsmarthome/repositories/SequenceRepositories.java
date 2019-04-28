package com.edu.fa.springmvcsmarthome.repositories;

import com.edu.fa.springmvcsmarthome.entities.Sequence;

public interface SequenceRepositories {
  /**
   * Get next _id of Document.
   * 
   * @param key Document's name
   * @return Sequence
   */
  public abstract Sequence getNextSequenceId(String key);
}
