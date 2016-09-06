const int relay = 2;
const int window = 7;
const int door = 4;

float referencevoltage = 5.0;

void setup()
{
  pinMode(relay, OUTPUT);
  digitalWrite(relay, LOW);
  pinMode(door, INPUT);
  pinMode(window, INPUT);
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
      Serial.print(doorstatus);
      Serial.print(" ");
      Serial.println(windowstatus);
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
  
  


