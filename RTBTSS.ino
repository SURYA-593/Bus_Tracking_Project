#include <ArduinoWiFiServer.h>
#include <BearSSLHelpers.h>
#include <CertStoreBearSSL.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiAP.h>
#include <ESP8266WiFiGeneric.h>
#include <ESP8266WiFiGratuitous.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266WiFiSTA.h>
#include <ESP8266WiFiScan.h>
#include <ESP8266WiFiType.h>
#include <WiFiClient.h>
#include <WiFiClientSecure.h>
#include <WiFiClientSecureBearSSL.h>
#include <WiFiServer.h>
#include <WiFiServerSecure.h>
#include <WiFiServerSecureBearSSL.h>
#include <WiFiUdp.h>
#define BLYNK_TEMPLATE_ID "TMPL3EWp3x_cH"
#define BLYNK_TEMPLATE_NAME "RTBTSS"
#define BLYNK_AUTH_TOKEN "AMeNCwsiXJQAOQxV4MDH5NMCnOQWpkkA"
#define BLYNK_PRINT Serial
#include <ESP8266WiFi.h>
#include <BlynkSimpleEsp8266.h>
char auth[] = "AMeNCwsiXJQAOQxV4MDH5NMCnOQWpkkA";
// Your WiFi credentials.
char ssid[] = "Arish";
char pass[] = "alexarish2";
const int irSensorPin = V2;  // Connect the IR sensor to digital pin D2
void setup() {
  Serial.begin(115200);
  Blynk.begin(auth, ssid, pass);
  pinMode(irSensorPin, INPUT);
}
void loop() {
  Blynk.run();
  int irSensorState = digitalRead(irSensorPin);
  if (irSensorState == LOW) {
    Serial.println("Object detected!");
    Blynk.virtualWrite(V1, 1);  // Assuming you have a Blynk LED widget on V1
  } else {
    Serial.println("NO Object detected.");
    Blynk.virtualWrite(V1, 0);  // Assuming you have a Blynk LED widget on V1
  }
delay(1000);  // Wait for 1 second before reading the sensor again
}
