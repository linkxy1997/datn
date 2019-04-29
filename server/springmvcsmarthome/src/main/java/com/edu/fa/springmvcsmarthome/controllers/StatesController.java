/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 23, 2019 7:00:11 PM
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.dto.States;
import com.edu.fa.springmvcsmarthome.entities.AirConditioner;
import com.edu.fa.springmvcsmarthome.entities.Humidity;
import com.edu.fa.springmvcsmarthome.entities.Led;
import com.edu.fa.springmvcsmarthome.entities.LightDependentResistor;
import com.edu.fa.springmvcsmarthome.entities.RainWaterSensor;
import com.edu.fa.springmvcsmarthome.entities.Temperature;
import com.edu.fa.springmvcsmarthome.services.AirConditionerService;
import com.edu.fa.springmvcsmarthome.services.HumidityService;
import com.edu.fa.springmvcsmarthome.services.LedService;
import com.edu.fa.springmvcsmarthome.services.LightDependentResistorService;
import com.edu.fa.springmvcsmarthome.services.RainWaterSensorService;
import com.edu.fa.springmvcsmarthome.services.SequenceService;
import com.edu.fa.springmvcsmarthome.services.TemperatureService;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@CrossOrigin
@RestController
@RequestMapping(value = "/states")
public class StatesController {
  @Autowired
  private HumidityService humidityService;
  @Autowired
  private LedService ledService;
  @Autowired
  private LightDependentResistorService lightDependentResistorService;
  @Autowired
  private RainWaterSensorService rainWaterSensorService;
  @Autowired
  private TemperatureService temperatureService;
  @Autowired
  private SequenceService sequenceService;
  @Autowired
  private AirConditionerService airConditionerService;

  /**
   * TODO
   * 
   * @return
   */
  @RequestMapping(value = "getStates", method = RequestMethod.GET, produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<States> getStates() {
    int ledStt = 0;
    Optional<Led> optionalLed = ledService.getCurrentLedStt();
    if (optionalLed.isPresent()) {
      ledStt = optionalLed.get().getLedStt();
    }
    float humidity = 0;
    Optional<Humidity> optionalHumidity = humidityService.getLastHumidity();
    if (optionalHumidity.isPresent()) {
      humidity = optionalHumidity.get().getHumidity();
    }
    int lightDependent = 0;
    Optional<LightDependentResistor> optionalLight = lightDependentResistorService
        .getLastLightDependentResistor();
    if (optionalLight.isPresent()) {
      lightDependent = optionalLight.get().getLightDependent();
    }
    float temperature = 0;
    Optional<Temperature> optionalTemp = temperatureService
        .getLastTemperature();
    if (optionalTemp.isPresent()) {
      temperature = optionalTemp.get().getTemperature();
    }
    int raintStatus = 0;
    Optional<RainWaterSensor> optionalRain = rainWaterSensorService
        .getLastRainWaterSensor();
    if (optionalRain.isPresent()) {
      raintStatus = optionalRain.get().getRaintStatus();
    }
    int airStt = 0;
    Optional<AirConditioner> optionalAir = airConditionerService
        .findTopByOrderByIdDesc();
    if (optionalAir.isPresent()) {
      airStt = optionalAir.get().getAirStt();
    }
    States states = new States(ledStt, humidity, temperature, lightDependent,
        raintStatus, airStt);
    return new ResponseEntity<States>(states, HttpStatus.OK);
  }

  /**
   * TODO
   * 
   * @param states
   * @return
   */
  @RequestMapping(value = "/setStates", method = RequestMethod.POST, produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<States> setStates(@RequestBody States states) {
    Date timeChange = new Date();
    int humidityId = sequenceService.getNextSequenceId(Constants.HUMI_SEQ_KEY);
    int temperatureId = sequenceService
        .getNextSequenceId(Constants.TEMP_SEQ_KEY);
    int lightDependentId = sequenceService
        .getNextSequenceId(Constants.LIGHT_DEPENDENT_RESISTOR);
    int raintStatusId = sequenceService
        .getNextSequenceId(Constants.RAIN_WATER_SENSOR);
    Humidity humidity = new Humidity(humidityId, states.getHumidity(),
        timeChange);
    Temperature temperature = new Temperature(temperatureId,
        states.getTemperature(), timeChange);
    LightDependentResistor resistor = new LightDependentResistor(
        lightDependentId, states.getLightDependent(), timeChange);
    RainWaterSensor waterSensor = new RainWaterSensor(raintStatusId,
        states.getRaintStatus(), timeChange);
    if (humidityService.save(humidity)
        && lightDependentResistorService.save(resistor)
        && temperatureService.saveTemperature(temperature)
        && rainWaterSensorService.save(waterSensor)) {
      return new ResponseEntity<States>(HttpStatus.CREATED);
    }
    return new ResponseEntity<States>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
