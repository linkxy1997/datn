/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 27, 2019 11:45:16 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.edu.fa.springmvcsmarthome.repositories")
public class MongoConfig extends AbstractMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "HomeIoT";
  }

  @Override
  public MongoClient mongoClient() {
    ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
    return new MongoClient(serverAddress,
        MongoClientOptions.builder().sslEnabled(false).build());
  }
}
