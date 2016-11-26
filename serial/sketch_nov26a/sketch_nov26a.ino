//################################################
//BALINT DECLARATIONS BEGIN
#define SERVO

//#define DEBUG
#define ICALLBACK

#ifdef SERVO
#include <Servo.h>
#endif
#include <dht.h>

dht DHT;
const int DHT11_PIN = 7;
const int relay = 2;
const int relay2 = 3;
const int relay3 = 4;
#ifndef SERVO
const int servopin = 13;
#endif

#ifdef SERVO
Servo myservo;
#endif
int pos = 0;
float referencevoltage = 5.0;
//add declarations
float temp = 0;
float temperature = 0;
int light = 0;
int lampstatus = 0;
int lampstatus2 = 0;
int lampstatus3 = 0;
int animation = 0;
int mode = 1;
int closed = 0;
//BALINT DECLARATIONS END
//###################################################
//LILLA DECLARATIONS BEGIN
#include <Adafruit_NeoPixel.h>

#define RIGHT 6 //pin for the right eye
#define LEFT 10 //pin for the left eye
#define NUMPIXELS 16 //nr of pixels
//#define LED 13

Adafruit_NeoPixel rightEye = Adafruit_NeoPixel(NUMPIXELS, RIGHT, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel leftEye = Adafruit_NeoPixel(NUMPIXELS, LEFT, NEO_GRB + NEO_KHZ800);
//LILLA DECLARATIONS END
//###################################################

void setup()
{
  //#################################################
  //BALINT SETUP BEGIN
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
  randomSeed(analogRead(0));
  Serial.begin(9600);
  //BALINT SETUP END
  //###################################################
  //LILLA SETUP BEGIN
  //##################################################
  rightEye.setBrightness(1);
  leftEye.setBrightness(1);
  rightEye.begin();
  leftEye.begin();
  //LILLA SETUP END
  //###################################################
  
}

void loop()
{
  /*
  temp = 0;
  for(int i=0; i<100; i++)
  {
    temp += analogRead(A5);
  }
  temperature = (temp*referencevoltage)/1023.0 - 2;
  light = analogRead(A4);
  lampstatus = bitRead(PORTD, relay);
  lampstatus2 = bitRead(PORTD, relay2);
  lampstatus3 = bitRead(PORTD, relay3);
  int chk = DHT.read11(DHT11_PIN);
  */
  if(mode == 1)
  {
    closed = 0;
    animation = random(1, 9);
    choose(animation);
  }
  else
  {
    if(closed == 0)
    {
      sleepForever();
      closed = 1;
    }
    callback();
  }
  
}

//######################################################
//BALINT FUNCTIONS BEGIN
void callback()
{
  if(Serial.available())
  {
    int n = Serial.parseInt();
    temp = 0;
    for(int i=0; i<100; i++)
    {
      temp += analogRead(A5);
    }
    temperature = (temp*referencevoltage)/1023.0 - 2;
    light = analogRead(A4);
    lampstatus = bitRead(PORTD, relay);
    lampstatus2 = bitRead(PORTD, relay2);
    lampstatus3 = bitRead(PORTD, relay3);
    int chk = DHT.read11(DHT11_PIN);
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
      //Serial.print(33);
      Serial.print(DHT.humidity);
      Serial.print("\n");
      //delay(1000);
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
      case 10:
      mode ^= 1;
      break;
      default:
      break;
    }
    //clip(1);
  }
}
//BALINT FUNCTIONS END
//###################################################
//LILLA FUNCTIONS BEGIN
void choose(int choice) {
  switch (choice) {
    case 1:
      //half circle wake up
      halfCircleWakeUp();
      break;
    case 2:
      //full circle wake up
      fullCircleWakeUp(2);
      break;
    case 3:
      //blink right
      closeEyesHalfCircle(1);
      delay(500);
      fullCircleWakeUp(1);
      break;
    case 4:
      //blink left
      closeEyesHalfCircle(0);
      delay(500);
      fullCircleWakeUp(0);
      break;
    case 5:
      //close both eyes
      closeEyesHalfCircle(2);
      break;
    case 6:
      //happy eyes
      happyEyes();
      break;
    case 7:
      //blink
      clip(3);
      fullCircleWakeUp(2);
      break;
    case 8:
      //rotate colors
      rotate();
      break;
    case 9:
      //roll eyes
      rollEyes();
      break;
    case 10:
      //close both eyes
      sleepForever();
      break;
    case 11:
      //emergency reset. use with caution
      reset();
      break;
    default:
      break;
  }
}





void halfCircleWakeUp() {
  //wake up animation for both eyes (half eyes)
  for (int i = 0; i < NUMPIXELS / 4; i++) {
    switch (i) {
      case 0:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
        rightEye.show();
        break;
      case 1:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
        rightEye.show();
        break;
      case 2:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
        rightEye.show();
        break;
      case 3:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
        rightEye.show();
        break;
      default:
        break;
    }
    #ifdef ICALLBACK
    callback();
    #endif
    delay(100);
  }
}



void fullCircleWakeUp(int right) {
  //wake up animation with eyes open
  if (right == 0) {
    //open left eye completely
    for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
      switch (i) {
        case 0:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
          leftEye.setPixelColor(i, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 255); // 4th quarter <-
          leftEye.show();
          break;
        case 1:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
          leftEye.setPixelColor(i, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 255); // 4th quarter <-
          leftEye.show();
          break;
        case 2:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
          leftEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
          leftEye.show();
          break;
        case 3:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
          leftEye.setPixelColor(i, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 197, 244, 66); // 4th quarter <-
          leftEye.show();
          break;
        default:
          break;
      }
      #ifdef ICALLBACK
      callback();
      #endif
      delay(100);
    }
  }
  else {
    if (right == 1) {
      //open right eye completely
      for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
        switch (i) {
          case 0:
            rightEye.setPixelColor(i, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 255); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
            rightEye.show();
            break;
          case 1:
            rightEye.setPixelColor(i, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 255); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
            rightEye.show();
            break;
          case 2:
            rightEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
            rightEye.show();
            break;
          case 3:
            rightEye.setPixelColor(i, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 197, 244, 66); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
            rightEye.show();
            break;
          default:
            break;
        }
        #ifdef ICALLBACK
        callback();
        #endif
        delay(100);
      }
    }
    else {
      //open both eyes completely
      for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
        switch (i) {
          case 0:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
            leftEye.setPixelColor(i, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 255); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 255); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
            rightEye.show();
            break;
          case 1:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
            leftEye.setPixelColor(i, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 255); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 255); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
            rightEye.show();
            break;
          case 2:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
            leftEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
            rightEye.show();
            break;
          case 3:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
            leftEye.setPixelColor(i, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, 197, 244, 66); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, 197, 244, 66); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
            rightEye.show();
            break;
          default:
            break;
        }
        #ifdef ICALLBACK
        callback();
        #endif
        delay(100);
      }
    }
  }
}




void reset() {
  //turn off every led on both eyes
  for (int i = 0; i < NUMPIXELS; i++) {
    leftEye.setPixelColor(i, 0, 0, 0);
    leftEye.show();
    rightEye.setPixelColor(i, 0, 0, 0);
    rightEye.show();
  }
}




void clip(int nrOfClips) {
  //blinking effect
  for (int j = 0; j < nrOfClips; j++) {
    //open both eyes
    for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
      switch (i) {
        case 0:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
          leftEye.setPixelColor(i, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 255); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 255); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 255);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 255);
          rightEye.show();
          break;
        case 1:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
          leftEye.setPixelColor(i, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 255); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 255); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 255);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 255);
          rightEye.show();
          break;
        case 2:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
          leftEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
          rightEye.show();
          break;
        case 3:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
          leftEye.setPixelColor(i, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, 197, 244, 66); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, 197, 244, 66); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, 197, 244, 66);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 197, 244, 66);
          rightEye.show();
          break;
        default:
          break;
      }
      #ifdef ICALLBACK
      callback();
      #endif
      delay(50);
    }
    #ifdef ICALLBACK
    callback();
    #endif
    delay(200);

    //close both eyes
    for (int i = 0; i < NUMPIXELS / 4; i++) {
      leftEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
      leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
      leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 0); // 2nd quarter ->
      leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 0); // 3rd quarter <-
      leftEye.show();
      rightEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
      rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
      rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 0); // 2nd quarter ->
      rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 0); // 3rd quarter <-
      rightEye.show();
      #ifdef ICALLBACK
      callback();
      #endif
      delay(50);
    }
    #ifdef ICALLBACK
    callback();
    #endif
    delay(200);
  }
}




void closeEyesHalfCircle(int right) {
  //close eyes
  if (right == 0) {
    //close left eye to half circle
    for (int i = 0; i < NUMPIXELS / 4; i++) {
      leftEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
      leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
      leftEye.show();
      #ifdef ICALLBACK
      callback();
      #endif
      delay(50);
    }
  }
  else {
    if (right == 1) {
      //close right eye to half circle
      for (int i = 0; i < NUMPIXELS / 4; i++) {
        rightEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
        rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
        rightEye.show();
        #ifdef ICALLBACK
        callback();
        #endif
        delay(50);
      }
    }
    else {
      //close both eyes to half circle
      for (int i = 0; i < NUMPIXELS / 4; i++) {
        rightEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
        rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
        rightEye.show();
        leftEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
        leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
        leftEye.show();
        #ifdef ICALLBACK
        callback();
        #endif
        delay(50);
      }
    }
  }
}




void happyEyes() {
  //upper half of both eyes
  for (int i = 0; i < NUMPIXELS / 4; i++) {
    leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 0); // 2nd quarter ->
    leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 0); // 3rd quarter <-
    leftEye.show();
    rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 0); // 2nd quarter ->
    rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 0); // 3rd quarter <-
    rightEye.show();
    #ifdef ICALLBACK
    callback();
    #endif
    delay(50);
  }
}





void sleepForever() {
  //close both eyes
  for (int i = 0; i < NUMPIXELS / 4; i++) {
    leftEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
    leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
    leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 0); // 2nd quarter ->
    leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 0); // 3rd quarter <-
    leftEye.show();
    rightEye.setPixelColor(i, 0, 0, 0); // 1st quarter ->
    rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0); // 4nd quarter <-
    rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 0, 0); // 2nd quarter ->
    rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 0, 0); // 3rd quarter <-
    rightEye.show();
    #ifdef ICALLBACK
    callback();
    #endif
    delay(50);
  }
}






void rotate() {
  //rotate colors (one cycle)
  for (int i = 1; i <= NUMPIXELS; i++) {
    //Serial.print("i = ");
    //Serial.println(i);
    for (int k = NUMPIXELS / 4 - 1; k >= 0 ; k--) {
      int j;
      if (i >= NUMPIXELS / 2) {
        j = i - NUMPIXELS / 2;
      }
      else {
        j = i;
      }
      //Serial.print("j = ");
      //Serial.println(j);
      //Serial.print("k = ");
      //Serial.println(k);
      switch (k) {
        case 0:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 255);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 255);
          }
          //2nd quarter
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 255); // 3rd quarter
          leftEye.setPixelColor(k + j, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            leftEye.setPixelColor(- k - 1 + j, 0, 0, 255); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 255); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 255);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 255);
          }
          //2nd quarter
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 255); // 3rd quarter
          rightEye.setPixelColor(k + j, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            rightEye.setPixelColor(- k - 1 + j, 0, 0, 255); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 255); // 4th quarter <-
          }
          rightEye.show();
          break;
        case 1:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 255);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 255);
          }
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 255);
          leftEye.setPixelColor(k + j, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            leftEye.setPixelColor(- k - 1 + j , 0, 255, 255); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 255); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 255);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 255);
          }
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 255);
          rightEye.setPixelColor(k + j, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            rightEye.setPixelColor(- k - 1 + j , 0, 255, 255); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 255); // 4th quarter <-
          }
          rightEye.show();
          break;
        case 2:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 0);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 0);
          }
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 0);
          leftEye.setPixelColor(k + j, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            leftEye.setPixelColor(- k - 1 + j, 0, 255, 0); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 0); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 0);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 0);
          }
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 0);
          rightEye.setPixelColor(k + j, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            rightEye.setPixelColor(- k - 1 + j, 0, 255, 0); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 0); // 4th quarter <-
          }
          rightEye.show();
          break;
        case 3:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 197, 244, 66);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 197, 244, 66);
          }
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 197, 244, 66);
          leftEye.setPixelColor(k + j, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            leftEye.setPixelColor(- k - 1 + j, 197, 244, 66); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 197, 244, 66); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 197, 244, 66);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 197, 244, 66);
          }
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 197, 244, 66);
          rightEye.setPixelColor(k + j, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            rightEye.setPixelColor(- k - 1 + j, 197, 244, 66); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 197, 244, 66); // 4th quarter <-
          }
          rightEye.show();
          break;
        default:
          break;
      }
    }
    #ifdef DEBUG
    Serial.println("callback");
    #endif
    #ifdef ICALLBACK
    callback();
    #endif
    delay(100);
  }
  #ifdef DEBUG
  Serial.println("done");
  #endif

}



void rollEyes() {
  for (int i = 0; i <= NUMPIXELS; i++) {
    #ifdef ICALLBACK
    callback();
    #endif
    #ifdef DEBUG
    Serial.print("i = ");
    Serial.println(i);
    #endif
    for (int k = NUMPIXELS / 4 - 1; k >= 0 ; k--) {
      int j;
      int a1, a2, a3, a4;
      if (i >= NUMPIXELS / 2) {
        j = i - NUMPIXELS / 2;
      }
      else {
        j = i;
      }
      if (j >= NUMPIXELS / 2) {
        j = j - NUMPIXELS / 2;
      }
      if (i - 2 < 0) {
        a1 = NUMPIXELS - 2 + i;
      }
      else {
        a1 = i - 2;
      }
      if (i - 1 < 0) {
        a2 = NUMPIXELS - 1;
      }
      else {
        a2 = i - 1;
      }
      if (i == NUMPIXELS) {
        a3 = 0;
      }
      else {
        a3 = i;
      }
      if (i + 1 >= NUMPIXELS) {
        a4 = i + 1 - NUMPIXELS;
      }
      else {
        a4 = i + 1;
      }
      #ifdef DEBUG
      Serial.print("j = ");
      Serial.println(j);
      Serial.print("k = ");
      Serial.println(k);
      Serial.print("a1 = ");
      Serial.println(a1);
      Serial.print("a2 = ");
      Serial.println(a2);
      Serial.print("a3 = ");
      Serial.println(a3);
      Serial.print("a4 = ");
      Serial.println(a4);
      #endif
      switch (k) {
        case 0:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            if (NUMPIXELS / 2 + k + j - NUMPIXELS == a1 || NUMPIXELS / 2 + k + j - NUMPIXELS == a2 || NUMPIXELS / 2 + k + j - NUMPIXELS == a3 || NUMPIXELS / 2 + k + j - NUMPIXELS == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 255);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 255);
            }
          }
          else {
            if (NUMPIXELS / 2 + k + j == a1 || NUMPIXELS / 2 + k + j == a2 || NUMPIXELS / 2 + k + j == a3 || NUMPIXELS / 2 + k + j == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 255);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 255);
            }
          }

          //2nd quarter
          if (NUMPIXELS / 2 - k - 1 + j == a1 || NUMPIXELS / 2 - k - 1 + j == a2 || NUMPIXELS / 2 - k - 1 + j == a3 || NUMPIXELS / 2 - k - 1 + j == a4) {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 255); // 3rd quarter
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 255);
          }

          if (k + j == a1 || k + j == a2 || k + j == a3 || k + j == a4) {
            leftEye.setPixelColor(k + j, 0, 0, 0);
            rightEye.setPixelColor(k + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(k + j, 0, 0, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(k + j, 0, 0, 255);
          }

          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            if (- k - 1 + j == a1 ||  - k - 1 + j == a2 || - k - 1 + j == a3 || - k - 1 + j == a4) {
              leftEye.setPixelColor(- k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(- k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(- k - 1 + j, 0, 0, 255); // 4th quarter <-
              rightEye.setPixelColor(- k - 1 + j, 0, 0, 255); // 4th quarter <-
            }
          }
          else {
            if (NUMPIXELS - k - 1 + j == a1 || NUMPIXELS - k - 1 + j == a2 || NUMPIXELS - k - 1 + j == a3 || NUMPIXELS - k - 1 + j == a4) {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 255); // 4th quarter <-
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 255);
            }
          }
          leftEye.show();
          rightEye.show();
          break;
        case 1:
          //left eye rotate

          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            if (NUMPIXELS / 2 + k + j - NUMPIXELS == a1 || NUMPIXELS / 2 + k + j - NUMPIXELS == a2 || NUMPIXELS / 2 + k + j - NUMPIXELS == a3 || NUMPIXELS / 2 + k + j - NUMPIXELS == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 255);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 255);
            }
          }
          else {
            if (NUMPIXELS / 2 + k + j == a1 || NUMPIXELS / 2 + k + j == a2 || NUMPIXELS / 2 + k + j == a3 || NUMPIXELS / 2 + k + j == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 255);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 255);
            }
          }
          //2nd quarter

          if (NUMPIXELS / 2 - k - 1 + j == a1 || NUMPIXELS / 2 - k - 1 + j == a2 || NUMPIXELS / 2 - k - 1 + j == a3 || NUMPIXELS / 2 - k - 1 + j == a4) {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 255); // 3rd quarter
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 255);
          }

          if (k + j == a1 || k + j == a2 || k + j == a3 || k + j == a4) {
            leftEye.setPixelColor(k + j, 0, 0, 0);
            rightEye.setPixelColor(k + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(k + j, 0, 255, 255); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(k + j, 0, 255, 255);
          }

          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            if (- k - 1 + j == a1 ||  - k - 1 + j == a2 || - k - 1 + j == a3 || - k - 1 + j == a4) {
              leftEye.setPixelColor(- k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(- k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(- k - 1 + j, 0, 255, 255); // 4th quarter <-
              rightEye.setPixelColor(- k - 1 + j, 0, 255, 255); // 4th quarter <-
            }
          }
          else {
            if (NUMPIXELS - k - 1 + j == a1 || NUMPIXELS - k - 1 + j == a2 || NUMPIXELS - k - 1 + j == a3 || NUMPIXELS - k - 1 + j == a4) {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 255); // 4th quarter <-
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 255);
            }
          }
          leftEye.show();
          rightEye.show();
          break;
        case 2:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            if (NUMPIXELS / 2 + k + j - NUMPIXELS == a1 || NUMPIXELS / 2 + k + j - NUMPIXELS == a2 || NUMPIXELS / 2 + k + j - NUMPIXELS == a3 || NUMPIXELS / 2 + k + j - NUMPIXELS == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 255, 0);
            }
          }
          else {
            if (NUMPIXELS / 2 + k + j == a1 || NUMPIXELS / 2 + k + j == a2 || NUMPIXELS / 2 + k + j == a3 || NUMPIXELS / 2 + k + j == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 255, 0);
            }
          }

          //2nd quarter
          if (NUMPIXELS / 2 - k - 1 + j == a1 || NUMPIXELS / 2 - k - 1 + j == a2 || NUMPIXELS / 2 - k - 1 + j == a3 || NUMPIXELS / 2 - k - 1 + j == a4) {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 0); // 3rd quarter
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 255, 0);
          }

          if (k + j == a1 || k + j == a2 || k + j == a3 || k + j == a4) {
            leftEye.setPixelColor(k + j, 0, 0, 0);
            rightEye.setPixelColor(k + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(k + j, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(k + j, 0, 255, 0);
          }

          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            if (- k - 1 + j == a1 ||  - k - 1 + j == a2 || - k - 1 + j == a3 || - k - 1 + j == a4) {
              leftEye.setPixelColor(- k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(- k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(- k - 1 + j, 0, 255, 0); // 4th quarter <-
              rightEye.setPixelColor(- k - 1 + j, 0, 255, 0); // 4th quarter <-
            }
          }
          else {
            if (NUMPIXELS - k - 1 + j == a1 || NUMPIXELS - k - 1 + j == a2 || NUMPIXELS - k - 1 + j == a3 || NUMPIXELS - k - 1 + j == a4) {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 0); // 4th quarter <-
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 255, 0);
            }
          }
          leftEye.show();
          rightEye.show();
          break;
        case 3:
          //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS) {
            if (NUMPIXELS / 2 + k + j - NUMPIXELS == a1 || NUMPIXELS / 2 + k + j - NUMPIXELS == a2 || NUMPIXELS / 2 + k + j - NUMPIXELS == a3 || NUMPIXELS / 2 + k + j - NUMPIXELS == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 197, 244, 66);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, 197, 244, 66);
            }
          }
          else {
            if (NUMPIXELS / 2 + k + j == a1 || NUMPIXELS / 2 + k + j == a2 || NUMPIXELS / 2 + k + j == a3 || NUMPIXELS / 2 + k + j == a4) {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS / 2 + k + j, 197, 244, 66);
              rightEye.setPixelColor(NUMPIXELS / 2 + k + j, 197, 244, 66);
            }
          }

          //2nd quarter
          if (NUMPIXELS / 2 - k - 1 + j == a1 || NUMPIXELS / 2 - k - 1 + j == a2 || NUMPIXELS / 2 - k - 1 + j == a3 || NUMPIXELS / 2 - k - 1 + j == a4) {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 197, 244, 66); // 3rd quarter
            rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, 197, 244, 66);
          }

          if (k + j == a1 || k + j == a2 || k + j == a3 || k + j == a4) {
            leftEye.setPixelColor(k + j, 0, 0, 0);
            rightEye.setPixelColor(k + j, 0, 0, 0);
          }
          else {
            leftEye.setPixelColor(k + j, 197, 244, 66); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(k + j, 197, 244, 66);
          }

          if (NUMPIXELS - k - 1 + j >= NUMPIXELS) {
            if (- k - 1 + j == a1 ||  - k - 1 + j == a2 || - k - 1 + j == a3 || - k - 1 + j == a4) {
              leftEye.setPixelColor(- k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(- k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(- k - 1 + j, 197, 244, 66); // 4th quarter <-
              rightEye.setPixelColor(- k - 1 + j, 197, 244, 66); // 4th quarter <-
            }
          }
          else {
            if (NUMPIXELS - k - 1 + j == a1 || NUMPIXELS - k - 1 + j == a2 || NUMPIXELS - k - 1 + j == a3 || NUMPIXELS - k - 1 + j == a4) {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 0, 0, 0);
            }
            else {
              leftEye.setPixelColor(NUMPIXELS - k - 1 + j, 197, 244, 66); // 4th quarter <-
              rightEye.setPixelColor(NUMPIXELS - k - 1 + j, 197, 244, 66);
            }
          }
          leftEye.show();
          rightEye.show();
          break;
        default:
          break;
      }
    }
  }
}
//LILLA FUNCTIONS END

