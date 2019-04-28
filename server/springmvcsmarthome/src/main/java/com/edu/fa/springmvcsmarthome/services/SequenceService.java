package com.edu.fa.springmvcsmarthome.services;

/**
 * Interface SequenceService.
 * 
 * @author linkx
 *
 */
public interface SequenceService {

  /**
   * Get next Document's _id.
   * 
   * @param key Document's Name.
   * @return the next Document's _id.
   */
  public abstract int getNextSequenceId(String key);
}
