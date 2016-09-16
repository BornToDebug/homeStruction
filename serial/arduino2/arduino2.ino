#include <Servo.h>

const int relay = 13;
const int relay2 = 3;
const int window = 7;
const int door = 4;
Servo myservo;
int pos = 0;
float referencevoltage = 5.0;

void setup()
{
  pinMode(relay, OUTPUT);
  pinMode(relay2, OUTPUT);
  digitalWrite(relay, LOW);
  digitalWrite(relay2, LOW);
  pinMode(door, INPUT);
  pinMode(window, INPUT);
  myservo.attach(9);
  myservo.write(0);
  Serial.begin(9600);
}

void loop()
{
  float temp = 0;
  for(int i=0; i<100; i++)
  {
    temp += analogRead(A5);
  }
  float temperature = (temp*referencevoltage)/1023.0 - 4;
  int light = analogRead(A4);
  int lampstatus = bitRead(PORTD, relay);
  int lampstatus2 = bitRead(PORTD, relay2);
  int doorstatus = digitalRead(door);
  int windowstatus = digitalRead(window);
  
  if(Serial.available())
  {
    int n = Serial.parseInt();
    switch(n)
    {
      case 1:
      digitalWrite(relay, HIGH);
      break;
      case 2:
      digitalWrite(relay, LOW);
      break;
      case 3:
      Serial.print(temperature);
      Serial.print(" ");
      Serial.print(light);
      Serial.print(" ");
      Serial.print(lampstatus);
      Serial.print(" ");
      Serial.print(lampstatus2);
      Serial.print(" ");
      Serial.print(doorstatus);
      Serial.print(" ");
      Serial.println(windowstatus);
      break;
      case 4:
      myservo.write(0);
      break;
      case 5:
      myservo.write(90);
      break;
      case 7:
      digitalWrite(relay2, HIGH);
      break;
      case 8:
      digitalWrite(relay2, LOW);
      break;
      default:
      break;
    }
  }
  
      /*Serial.print(temperature);
      Serial.print(" ");
      Serial.print(light);
      Serial.print(" ");
      Serial.print(lampstatus);
      Serial.print(" ");
      Serial.print(doorstatus);
      Serial.print(" ");
      Serial.println(windowstatus);
  delay(10000);*/
}
  
  


