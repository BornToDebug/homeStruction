const int relay = 2;
int n = 0;
float referencevoltage = 5.0;
//float temp = 0;
//float temperature = 0;
int light = 0;
void setup()
{
  pinMode(relay, OUTPUT);
  digitalWrite(relay, LOW);
  Serial.begin(9600);
  //analogReference(INTERNAL);
  referencevoltage = 5;
}

void loop()
{
  float temp = 0;
  for(int i=0; i<100; i++)
  {
    temp += analogRead(A5);
  }
  float temperature = (temp*referencevoltage)/1023.0 - 4;
  //Serial.println(temperature);
  light = analogRead(A4);
  if(Serial.available())
  {
    n = Serial.parseInt();
    command(n, temperature);
  }

  delay(500);
}

void command(int n, float temperature)
{
  switch(n)
  {
    case 10:
    digitalWrite(relay, LOW);
    break;
    case 11:
    digitalWrite(relay, HIGH);
    break;
    case 12:
    Serial.println(bitRead(PORTD, relay));
    break;
    case 20:
//    Serial.println(temp);
    Serial.println(temperature);
    break;
    case 30:
    Serial.println(light);
    break;
    default:
    break;
  }
}

