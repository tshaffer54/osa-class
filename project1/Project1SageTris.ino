/*
  LiquidCrystal Library - Hello World

  Demonstrates the use a 16x2 LCD display.  The LiquidCrystal
  library works with all LCD displays that are compatible with the
  Hitachi HD44780 driver. There are many of them out there, and you
  can usually tell them by the 16-pin interface.

  This sketch prints "Hello World!" to the LCD
  and shows the time.

  The circuit:
   LCD RS pin to digital pin 12
   LCD Enable pin to digital pin 11
   LCD D4 pin to digital pin 5`
   LCD D5 pin to digital pin 4
   LCD D6 pin to digital pin 3
   LCD D7 pin to digital pin 2
   LCD R/W pin to ground
   LCD VSS pin to ground
   LCD VCC pin to 5V
   10K resistor:
   ends to +5V and ground
   wiper to LCD VO pin (pin 3)

  Library originally added 18 Apr 2008
  by David A. Mellis
  library modified 5 Jul 2009
  by Limor Fried (http://www.ladyada.net)
  example added 9 Jul 2009
  by Tom Igoe
  modified 22 Nov 2010
  by Tom Igoe
  modified 7 Nov 2016
  by Arturo Guadalupi

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/LiquidCrystalHelloWorld

*/

// include the library code:
#include <LiquidCrystal.h>

// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
int e = 7;
int a = 0;
int green = 9;
int red = 8;
int sensorPin = 0;
float cel = 0.0;
float fah = 0.0;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void setup() {
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
  // Print a message to the LCD.
  lcd.print("Welcome");
  pinMode(e, INPUT);
  pinMode(green, OUTPUT);
  pinMode(red, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  int button = digitalRead(e);

  lcd.setCursor(10, 0);
  lcd.print(a);

  int reading = analogRead(sensorPin);

  float voltage = reading * 5.0;
  voltage /= 1024.0;
  // Serial.print(voltage);
  // Serial.println(" volts");

  float temperatureC = ((voltage - 0.5) * 100) * -1 ;
  
  // Serial.print(temperatureC);
  // Serial.println(" degrees C");

  // now convert to Fahrenheit
  float temperatureF = (temperatureC * 9.0 / 5.0) + 32.0;
  // Serial.print(temperatureF);
  // Serial.println(" degrees F");

  if (button == HIGH) {
    a ++;
    lcd.setCursor(10, 0);
    lcd.print(a);
    delay(200);
    if (a == 5) {
      a = 0;
      if (digitalRead(green) == LOW && digitalRead(red) == LOW) {
        digitalWrite(green, HIGH);
        lcd.setCursor(0, 1);
        lcd.print("Temp C:");
        cel = temperatureC;
        lcd.print(cel);
      } else if (digitalRead(green) == HIGH) {
        digitalWrite(green, LOW);
        digitalWrite(red, HIGH);
        lcd.setCursor(0, 1);
        lcd.print("Temp F:");
        fah = temperatureF;
        lcd.print(fah);
      } else if (digitalRead(red) == HIGH) {
        digitalWrite(red, LOW);
        Serial.println("Measured Temp. was:");
        Serial.print("Temp C:");
        Serial.println(cel);
        Serial.print("Temp F:");
        Serial.println(fah);
        lcd.setCursor(0,1);
        lcd.print("Temp. Saved     ");
      }
      lcd.setCursor(10, 0);
      lcd.print(a);
    }
  }
}