const int relay = 2;
const int window = 7;
const int door = 4;

int n = 0;
float referencevoltage = 5.0;
//float temp = 0;
//float temperature = 0;
int light = 0;
void setup()
{
  pinMode(relay, OUTPUT);
  digitalWrite(relay, LOW);
  pinMode(door, INPUT);
  pinMode(window, INPUT);
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
    case 10: //Turn the light off
    digitalWrite(relay, LOW);
    break;
    case 11: //Turn the light on
    digitalWrite(relay, HIGH);
    break;
    case 12: //Read lamp status
    Serial.print("lamp ");
    Serial.println(bitRead(PORTD, relay));
    break;
    case 20: //Send temperature
//    Serial.println(temp);
    Serial.print("temp ");
    Serial.println(temperature);
    break;
    case 30: //Send light
    Serial.print("light ");
    Serial.println(light);
    break;
    case 40: //Door status
    Serial.print("door ");
    Serial.println(digitalRead(door));
    break;
    case 50: //Window status
    Serial.print("window ");
    Serial.println(digitalRead(window));
    break;
    default:
    break;
  }
}

