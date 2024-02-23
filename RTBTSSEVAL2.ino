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
#include <TinyGPS++.h>
#define BLYNK_TEMPLATE_ID "TMPL3EWp3x_cH"
#define BLYNK_TEMPLATE_NAME "RTBTSS"
#define BLYNK_AUTH_TOKEN "AMeNCwsiXJQAOQxV4MDH5NMCnOQWpkkA"
#define BLYNK_PRINT Serial
#include <ESP8266WiFi.h>
#include <BlynkSimpleEsp8266.h>
char auth[] = "AMeNCwsiXJQAOQxV4MDH5NMCnOQWpkkA";
char ssid[] = "Arish";
char pass[] = "alexarish2";
const int irSensorPin = V2;
const int gpsTxPin = 4;  // D4 on ESP8266
const int gpsRxPin = 5;  // D5 on ESP8266
HardwareSerial gpsSerial(1);  // Use the second hardware serial port
TinyGPSPlus gps;  // Declare the gps object

void setup() {
  Serial.begin(115200);
  gpsSerial.begin(9600);  // GPS module communication speed
  Blynk.begin(auth, ssid, pass);
  pinMode(irSensorPin, INPUT);
}
void loop() {
  Blynk.run();
  int irSensorState = digitalRead(irSensorPin);
  if (irSensorState == LOW) {
    Serial.println("Object detected!");
    Blynk.virtualWrite(V1, 1);
  } else {
    Serial.println("NO Object detected.");
    Blynk.virtualWrite(V1, 0);
  }
  while (gpsSerial.available() > 0) {
    if (gps.encode(gpsSerial.read())) {
      if (gps.location.isValid()) {
        double latitude = gps.location.lat();
        double longitude = gps.location.lng();
        Serial.print("Latitude: ");
        Serial.println(latitude, 6);
        Serial.print("Longitude: ");
        Serial.println(longitude, 6);
        Blynk.virtualWrite(V3, latitude);
        Blynk.virtualWrite(V4, longitude);
      }
    }
  }
  delay(1000);
}
