
package com.edu.fa.springmvcsmarthome.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.entities.Led;
import com.edu.fa.springmvcsmarthome.services.LedService;
import com.edu.fa.springmvcsmarthome.services.SequenceService;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/led")
public class LedController {
  @Autowired
  private LedService ledService;
  @Autowired
  private SequenceService sequenceService;

  /**
   * Save a new led to Database.
   *
   * @param led led object from client
   * @return new led
   */
  @PostMapping(value = "/save", produces = { MediaType.APPLICATION_JSON_VALUE })
  @ResponseBody
  public ResponseEntity<Led> save(@RequestBody Led led) {
    led.setLedId(sequenceService.getNextSequenceId(Constants.LED_SEQ_KEY));
    led.setTimeChange(new Date());
    if (ledService.save(led)) {
      return new ResponseEntity<>(led, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Parse a file and print the parse tree.
   *
   * @return the AST of the file in String form.
   * @throws IOException         if the file could not be read.
   * @throws CheckstyleException if the file is not a Java source.
   */
  @GetMapping(value = "")
  public String welcome() {
    return "Hello spring mvc";
  }
}
