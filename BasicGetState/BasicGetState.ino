/**
    BasicGetState.ino

    Created on: 1.05.2019
    Author: linkx
    For KIT Wemos D1 R2 Post data to Server

*/

#include <Arduino.h>

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

#include <ESP8266HTTPClient.h>

#include <ArduinoJson.h>
#include "DHTesp.h"
#include <WiFiClient.h>

ESP8266WiFiMulti WiFiMulti;
DHTesp dht;

const char* ssid = "NMT";
const char* password = "24950381";
const String HOST = "192.168.1.103";
const uint16_t PORT = 8080;
const String API_URI_POST = "/api/states/setStates";
const char* AUTHORIZATION = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDM2NDgzOTksInVzZXJuYW1lIjoiYWRtaW4ifQ.aTttSa1-rFdU11E7IAiR9GPOYHlPpZ1Ue5wSAMSgEqM";
float temperature, humidity;
int rain, light, autoState;
void setup() {

  Serial.begin(115200);
  // Serial.setDebugOutput(true);

  dht.setup(D6, DHTesp::DHT11);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(1000);
    Serial.println("Connecting...");
  }
}
void setStates() {
  StaticJsonBuffer<300> JSONbuffer;   //Declaring static JSON buffer
  JsonObject& JSONencoder = JSONbuffer.createObject(); //Initializing JsonObject
  //Set key&value to JsonObject
  JSONencoder["humidity"] = String(humidity, 2);
  JSONencoder["temperature"] = String(temperature, 2);
  JSONencoder["lightDependent"] = String(light);
  JSONencoder["raintStatus"] = String(rain);  
  //Declaring char array
  char data[300];
  //Transfer JsonObject to String
  JSONencoder.prettyPrintTo(data, sizeof(data));
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;
    Serial.print("[HTTP] begin...\n");
    if (http.begin(HOST, PORT, API_URI_POST)) { // HTTP
      http.addHeader("Content-Type", "application/json");
      http.addHeader("Authorization", AUTHORIZATION);
      Serial.print("[HTTP] GET...\n");
      // start connection and send HTTP header
      int httpCode = http.POST(data);

      // httpCode will be negative on error
      if (httpCode > 0) {
        // HTTP header has been send and Server response header has been handled
        Serial.printf("[HTTP] POST... code: %d\n", httpCode);

        // Json found at server
        if (httpCode == HTTP_CODE_CREATED || httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
          String payload = http.getString();
          Serial.println(payload);
        }
      } else {
        Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
      }

      http.end();
    } else {
      Serial.printf("[HTTP} Unable to connect\n");
    }
  }
}
void loop() {
  // wait for WiFi connection
  delay(3000);
  humidity = dht.getHumidity();
  temperature = dht.getTemperature();
  rain = digitalRead(D5);
  light = analogRead(A0);
  setStates();
}
