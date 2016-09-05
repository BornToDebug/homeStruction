void setup()
{
  Serial.begin(14400);
}

void loop()
{
  int light = analogRead(A4);
  Serial.println(light);
  delay(1000);
}
