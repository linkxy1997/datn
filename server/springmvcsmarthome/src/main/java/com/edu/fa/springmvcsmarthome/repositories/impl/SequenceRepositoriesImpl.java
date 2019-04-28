package com.edu.fa.springmvcsmarthome.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.edu.fa.springmvcsmarthome.entities.Sequence;
import com.edu.fa.springmvcsmarthome.repositories.SequenceRepositories;

@Repository
public class SequenceRepositoriesImpl implements SequenceRepositories {
  @Autowired
  private MongoOperations mongoOperations;

  /*
   * (non-Javadoc)
   * 
   * @see com.edu.fa.springmvchomeiot.reponsitories.SequenceRepositories#
   * getNextSequenceId(java.lang.String)
   */
  @Override
  public Sequence getNextSequenceId(String key) {
    // TODO Auto-generated method stub
    Query query = new Query(Criteria.where("_id").is(key));
    // increase sequence id by 1
    Update update = new Update();
    update.inc("seq", 1);

    // return new increased id
    FindAndModifyOptions options = new FindAndModifyOptions();
    options.returnNew(true);

    // this is the magic happened.
    Sequence seqId = mongoOperations.findAndModify(query, update, options, Sequence.class);
    return seqId;
  }

}
