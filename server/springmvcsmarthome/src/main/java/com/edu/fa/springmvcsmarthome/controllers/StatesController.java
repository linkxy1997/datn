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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.dto.States;
import com.edu.fa.springmvcsmarthome.entities.AirConditioner;
import com.edu.fa.springmvcsmarthome.entities.Automation;
import com.edu.fa.springmvcsmarthome.entities.Humidity;
import com.edu.fa.springmvcsmarthome.entities.Led;
import com.edu.fa.springmvcsmarthome.entities.LightDependentResistor;
import com.edu.fa.springmvcsmarthome.entities.RainWaterSensor;
import com.edu.fa.springmvcsmarthome.entities.Temperature;
import com.edu.fa.springmvcsmarthome.services.AirConditionerService;
import com.edu.fa.springmvcsmarthome.services.AutomationStateService;
import com.edu.fa.springmvcsmarthome.services.HumidityService;
import com.edu.fa.springmvcsmarthome.services.LedService;
import com.edu.fa.springmvcsmarthome.services.LightDependentResistorService;
import com.edu.fa.springmvcsmarthome.services.RainWaterSensorService;
import com.edu.fa.springmvcsmarthome.services.SequenceService;
import com.edu.fa.springmvcsmarthome.services.TemperatureService;
import com.edu.fa.springmvcsmarthome.utils.Constants;

/**
 * 
 * @author linkx
 * 
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/states")
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
  @Autowired
  private AutomationStateService autoService;

  /**
   * Send States to Wemos D1 R2.
   * 
   * @return ResponseEntity HttpStatus.OK if Wemos D1 R2 GET complete.
   */
  @GetMapping(value = "getStates", produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<States> getStates() {
    // Start get Persistent Object from Database
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
    int autoState = 0;
    Optional<Automation> optionalAutoState = autoService.findOne(1);
    if (optionalAutoState.isPresent()) {
      autoState = optionalAutoState.get().getAutomationState();
    }
    // Transfer to States Dto Object
    States states = new States(ledStt, humidity, temperature, lightDependent,
        raintStatus, airStt, autoState);
    // Expose to API json
    return new ResponseEntity<>(states, HttpStatus.OK);
  }

  /**
   * Receive States from Wemos D1 R2.
   * 
   * @param states: Humidity,Temperature,RainSensor,LightSensor.
   * @return ResponseEntity: HttpStatus.OK if set true.
   * @throws MongoExceptionTranslator.
   */
  @PostMapping(value = "/setStates", produces = {
      MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<States> setStates(@RequestBody States states) {
    // Start transfer States to Persistent Object
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
    // Convert to percent
    int light = 1024 - states.getLightDependent();
    int lightDependent = (int) (light * 100 / 1024);
    LightDependentResistor resistor = new LightDependentResistor(
        lightDependentId, lightDependent, timeChange);
    RainWaterSensor waterSensor = new RainWaterSensor(raintStatusId,
        states.getRaintStatus(), timeChange);
    // Begin save Persistent Object
    if (humidityService.save(humidity)
        && lightDependentResistorService.save(resistor)
        && temperatureService.saveTemperature(temperature)
        && rainWaterSensorService.save(waterSensor)) {
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
