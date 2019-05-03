/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 28, 2019 3:48:11 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.controllers;

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

import com.edu.fa.springmvcsmarthome.entities.AutomationState;
import com.edu.fa.springmvcsmarthome.services.AutomationStateService;

@CrossOrigin
@RequestMapping(value = "/api/automation")
@RestController
public class AutomationStateController {
  @Autowired
  private AutomationStateService automationStateService;

  @PostMapping(value = "/setState", produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<AutomationState> updateAutomationState(
      @RequestBody AutomationState automationState) {
    automationState.setId(1);
    if (automationStateService.saveOrUpdate(automationState)) {
      return new ResponseEntity<>(automationState, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping(value = "/getState", produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<AutomationState> getAutomationState() {
    Optional<AutomationState> optional = automationStateService.findOne(1);
    if (optional.isPresent()) {
      return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    } else {
      AutomationState automationState = new AutomationState(1, 0);
      automationStateService.saveOrUpdate(automationState);
      return new ResponseEntity<>(automationState, HttpStatus.CREATED);
    }
  }
}
