/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 25, 2019 4:15:24 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.entities.AirConditioner;
import com.edu.fa.springmvcsmarthome.services.AirConditionerService;
import com.edu.fa.springmvcsmarthome.services.SequenceService;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/air")
public class AirConditionerController {
  @Autowired
  private AirConditionerService airConditionerService;
  @Autowired
  private SequenceService sequenceService;

  @GetMapping(value = "/getAirStatus", produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<AirConditioner> getAirConditionerStatus() {
    Optional<AirConditioner> optional = airConditionerService
        .findTopByOrderByIdDesc();
    if (optional.isPresent()) {
      return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping(value = "/postAirStatus", produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<AirConditioner> postAirConditionerStatus(
      @RequestBody AirConditioner airConditioner) {
    airConditioner.setTimeChange(new Date());
    airConditioner
        .setId(sequenceService.getNextSequenceId(Constants.AIR_CONDITIONER));
    if (airConditionerService.save(airConditioner)) {
      return new ResponseEntity<>(airConditioner, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
