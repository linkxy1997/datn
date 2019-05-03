/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 3:17:16 PM Apr 7, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.entities.Temperature;
import com.edu.fa.springmvcsmarthome.services.SequenceService;
import com.edu.fa.springmvcsmarthome.services.TemperatureService;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@CrossOrigin
@RestController
@RequestMapping(value = "temp")
public class TemperatureController {
  @Autowired
  private TemperatureService temperatureService;
  @Autowired
  private SequenceService sequenceService;

  /**
   *  save new Temperature.
   *
   * @param temp temp.
   * @return
   */

  @PostMapping(value = "/save", produces = { MediaType.APPLICATION_JSON_VALUE })
  public boolean saveTemperature(@ModelAttribute Temperature temperature) {
    temperature.setTemperatureId(
        sequenceService.getNextSequenceId(Constants.TEMP_SEQ_KEY));
    temperature.setTimeAdd(new Date());
    return temperatureService.saveTemperature(temperature);
  }
}
