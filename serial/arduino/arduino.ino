const int relay = 2;
int n = 0;
int temp = 0;
float temperature = 0;
float referencevoltage;
int light = 0;
int sound = 0;

void setup()
{
  pinMode(relay, OUTPUT);
  digitalWrite(relay, LOW);
  Serial.begin(9600);
  analogReference(INTERNAL);
  referencevoltage = 1.1;
}

void loop()
{
  if(Serial.available())
  {
    n = Serial.parseInt();
    command(n);
  }
  temp = analogRead(A5);
  temperature = (temp*referencevoltage*100)/1023 - 4;
  light = analogRead(A4);
  delay(100);
}

void command(int n)
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

