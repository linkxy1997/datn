package com.edu.fa.springmvcsmarthome.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fa.springmvcsmarthome.entities.Sequence;

/*
 * @author linkx
 *
 */
public interface SequenceRepository extends MongoRepository<Sequence, String> {

}
