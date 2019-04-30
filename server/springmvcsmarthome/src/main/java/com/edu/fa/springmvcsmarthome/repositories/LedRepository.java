
package com.edu.fa.springmvcsmarthome.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edu.fa.springmvcsmarthome.entities.Led;

/**
 * Extension of {@link MongoRepository} to provide additional methods to
 * retrieve entities using the pagination and sorting abstraction.
 * 
 * @author linkx
 */
@Repository
public interface LedRepository extends MongoRepository<Led, Integer> {
  /**
   * Get first index of Led from Database.
   *
   * @return
   */
  Optional<Led> findTopByOrderByLedIdDesc();
}
