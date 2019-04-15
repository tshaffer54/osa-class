#include <MultiStepper.h>
#include <AccelStepper.h>
#include <Servo.h>

#define HALFSTEP 8

// Motor pin definitions
#define motorPin1  3     // IN1 on the ULN2003 driver 1
#define motorPin2  4     // IN2 on the ULN2003 driver 1
#define motorPin3  5     // IN3 on the ULN2003 driver 1
#define motorPin4  6     // IN4 on the ULN2003 driver 1

const int ldrPin = A0;

// Initialize with pin sequence IN1-IN3-IN2-IN4 for using the AccelStepper with 28BYJ-48
AccelStepper stepper1(HALFSTEP, motorPin1, motorPin3, motorPin2, motorPin4);
Servo dayNight;

int pos = 0;

void setup() {

  Serial.begin(9600);
  pinMode(ldrPin, INPUT);

  dayNight.attach(9);
  
  stepper1.setMaxSpeed(1000.0);
  stepper1.setAcceleration(100.0);
  stepper1.setSpeed(200);
  stepper1.moveTo(20000);

}//--(end setup )---

void loop() {
  int currentLight = analogRead(ldrPin);
  if (stepper1.distanceToGo() == 0) {
    stepper1.moveTo(-stepper1.currentPosition());
  }
  stepper1.run();

  if (currentLight <= 790) {
    Serial.println("night");
    if (pos != 180) {
      dayNight.write(180);
      pos = 180;
      delay(15);
    }
  } else {
    Serial.println("day");
    dayNight.write(0);
    pos = 0;
    delay(15);
    }
  }