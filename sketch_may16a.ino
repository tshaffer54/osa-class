void setup() {
  pinMode(12, OUTPUT);
  Serial.begin(9600);

}  

void loop() {
  if (Serial.available()) {
    light(Serial.read());
  }
}

void light(int n) {
  if (n == 49) {
    digitalWrite(12, HIGH);
  } else {
    digitalWrite(12, LOW);
  }

}
