
//#define SERVO

#ifdef SERVO
#include <Servo.h>
#endif
//#include <dht.h>

//dht DHT;
//const int DHT11_PIN = 7;
const int relay = 2;
const int relay2 = 3;
const int relay3 = 4;
#ifndef SERVO
const int servopin = 9;
#endif

#ifdef SERVO
Servo myservo;
#endif
int pos = 0;
float referencevoltage = 5.0;

void setup()
{
  pinMode(relay, OUTPUT);
  pinMode(relay2, OUTPUT);
  pinMode(relay3, OUTPUT);
  digitalWrite(relay, LOW);
  digitalWrite(relay2, LOW);
  digitalWrite(relay3, LOW);
  #ifndef SERVO
  pinMode(servopin, OUTPUT);
  digitalWrite(servopin, LOW);
  #endif
  //myservo.attach(9);
  //myservo.write(0);
  Serial.begin(9600);
}

void loop()
{
  float temp = 0;
  for(int i=0; i<100; i++)
  {
    temp += analogRead(A5);
  }
  float temperature = (temp*referencevoltage)/1023.0 - 2;
  int light = analogRead(A4);
  int lampstatus = bitRead(PORTD, relay);
  int lampstatus2 = bitRead(PORTD, relay2);
  int lampstatus3 = bitRead(PORTD, relay3);
  //int chk = DHT.read11(DHT11_PIN);
  
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
      Serial.print(lampstatus3);
      Serial.print(" ");
      #ifdef SERVO
      Serial.print(myservo.read());
      Serial.print(" ");
      #endif
      Serial.print(33);
      //Serial.print(DHT.humidity);
      Serial.print("\n");
      break;
      case 4:
      #ifdef SERVO
      myservo.attach(9);
      myservo.write(10);
      delay(500);
      myservo.detach();
      #endif
      #ifndef SERVO
      digitalWrite(servopin, 1);
      delay(1000);
      digitalWrite(servopin, 0);
      #endif
      break;
      case 5:
      #ifdef SERVO
      myservo.attach(9);
      myservo.write(72);
      delay(500);
      myservo.detach();
      #endif
      #ifndef SERVO
      digitalWrite(servopin, 1);
      delay(1000);
      digitalWrite(servopin, 0);
      #endif
      break;
      case 7:
      digitalWrite(relay2, HIGH);
      break;
      case 8:
      digitalWrite(relay2, LOW);
      break;
      case 6:
      digitalWrite(relay3, HIGH);
      break;
      case 9:
      digitalWrite(relay3, LOW);
      break;
      default:
      break;
    }
  }
}
