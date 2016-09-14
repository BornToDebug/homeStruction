const int ind = 7;
const int inc = 6;
const int inb = 5;
const int ina = 4;

void setup()
{
  pinMode(ina, OUTPUT);
  pinMode(inb, OUTPUT);
  pinMode(inc, OUTPUT);
  pinMode(ind, OUTPUT);
}

void loop()
{
  int i = 0;
  for (i=0; i<1024; i++)
  {
    clockwiserotate();
  }
  delay(1000);
  for (i=0; i<1024; i++)
  {
    counterclockwiserotate();
  }
  delay(1000);
}

void clockwiserotate()
{
  step1();
  step2();
  step3();
  step4();
  step5();
  step6();
  step7();
  step8();
}

void counterclockwiserotate()
{
  step8();
  step7();
  step6();
  step5();
  step4();
  step3();
  step2();
  step1();
}

void step1()
{
  digitalWrite(ina, HIGH);
  digitalWrite(inb, LOW);
  digitalWrite(inc, LOW);
  digitalWrite(ind, LOW);
  delay(2);
}
void step2()
{
  digitalWrite(ina, HIGH);
  digitalWrite(inb, HIGH);
  digitalWrite(inc, LOW);
  digitalWrite(ind, LOW);
  delay(2);
}
void step3()
{
  digitalWrite(ina, LOW);
  digitalWrite(inb, HIGH);
  digitalWrite(inc, LOW);
  digitalWrite(ind, LOW);
  delay(2);
}
void step4()
{
  digitalWrite(ina, LOW);
  digitalWrite(inb, HIGH);
  digitalWrite(inc, HIGH);
  digitalWrite(ind, LOW);
  delay(2);
}
void step5()
{
  digitalWrite(ina, LOW);
  digitalWrite(inb, LOW);
  digitalWrite(inc, HIGH);
  digitalWrite(ind, LOW);
  delay(2);
}
void step6()
{
  digitalWrite(ina, LOW);
  digitalWrite(inb, LOW);
  digitalWrite(inc, HIGH);
  digitalWrite(ind, HIGH);
  delay(2);
}
void step7()
{
  digitalWrite(ina, LOW);
  digitalWrite(inb, LOW);
  digitalWrite(inc, LOW);
  digitalWrite(ind, HIGH);
  delay(2);
}
void step8()
{
  digitalWrite(ina, HIGH);
  digitalWrite(inb, LOW);
  digitalWrite(inc, LOW);
  digitalWrite(ind, HIGH);
  delay(2);
}
