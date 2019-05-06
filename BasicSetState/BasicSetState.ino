/**
    BasicSetState.ino

    Created on: 1.05.2019
    Author: linkx
    For KIT Wemos D1 R2 Post data to Server

*/
#include <Arduino.h>

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

#include <ESP8266HTTPClient.h>

#include <ArduinoJson.h>
#include <WiFiClient.h>

const char* ssid = "NMT";
const char* password = "24950381";
const String HOST = "192.168.1.104";
const uint16_t PORT = 8080;
const String API_URI_GET = "/api/states/getStates";
const char* AUTHORIZATION = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDM2NDgzOTksInVzZXJuYW1lIjoiYWRtaW4ifQ.aTttSa1-rFdU11E7IAiR9GPOYHlPpZ1Ue5wSAMSgEqM";
int autoState, ledStt, airStt, raintStatus;
float temperature, humidity, lightDependent;
void setup() {
  // put your setup code here, to run once:
  pinMode(D5, OUTPUT);
  pinMode(D6, OUTPUT);
  pinMode(D7, OUTPUT);
  Serial.begin(115200);
  // Serial.setDebugOutput(true);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(1000);
    Serial.println("Connecting...");
  }
}
void setStates() {
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;
    Serial.print("[HTTP] begin...\n");
    if (http.begin(HOST, PORT, API_URI_GET)) { // HTTP
      http.addHeader("Content-Type", "application/json");
      http.addHeader("Authorization", AUTHORIZATION);
      Serial.print("[HTTP] GET...\n");
      // start connection and send HTTP header
      int httpCode = http.GET();

      // httpCode will be negative on error
      if (httpCode > 0) {
        // HTTP header has been send and Server response header has been handled
        Serial.printf("[HTTP] GET... code: %d\n", httpCode);

        // file found at server
        if (httpCode == HTTP_CODE_OK || httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
          const size_t bufferSize = JSON_OBJECT_SIZE(2) + JSON_OBJECT_SIZE(3) + JSON_OBJECT_SIZE(5) + JSON_OBJECT_SIZE(8) + 370;
          DynamicJsonBuffer jsonBuffer(bufferSize);
          JsonObject& root = jsonBuffer.parseObject(http.getString());
          autoState = root["autoState"];
          temperature = root["temperature"];
          ledStt = root["ledStt"];
          airStt = root["airStt"];
          raintStatus = root["raintStatus"];
          lightDependent = root["lightDependent"];
          if (autoState) {
            //Enable Automation States
            controllAir();
            controllLed();
          } else {
            digitalWrite(D5, airStt);
            digitalWrite(D6, ledStt);
          }
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
void controllAir() {
  //If temp > 29 => turn on
  if (temperature > 29) {
    digitalWrite(D5, HIGH);
  } else {
    digitalWrite(D5, LOW);
  }
}
void controllLed() {
  if (lightDependent < 50) {
    digitalWrite(D6, HIGH);
  } else {
    digitalWrite(D6, LOW);
  }
}
void loop() {
  // put your main code here, to run repeatedly:
  setStates();
}
