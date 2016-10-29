#include <Adafruit_NeoPixel.h>

#define RIGHT 6 //pin for the right eye
#define LEFT 10 //pin for the left eye
#define NUMPIXELS 16 //nr of pixels

Adafruit_NeoPixel rightEye = Adafruit_NeoPixel(NUMPIXELS, RIGHT, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel leftEye = Adafruit_NeoPixel(NUMPIXELS, LEFT, NEO_GRB + NEO_KHZ800);

void setup() {
  rightEye.setBrightness(1);
  leftEye.setBrightness(1);
  rightEye.begin();
  leftEye.begin();
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    int incoming = 0;
    incoming = Serial.parseInt();
    Serial.print("I received: ");
    Serial.println(incoming);
    switch (incoming) {
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
        //close both eyes
        sleepForever();
        break;
      case 9:
        //emergency reset. use with caution
        reset();
        break;
      default:
        break;
    }
  }
}

void halfCircleWakeUp() {
  //wake up animation for both eyes (half eyes)
  for (int i = 0; i < NUMPIXELS / 4; i++) {
    leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
    leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
    leftEye.show();
    rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0);
    rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0);
    rightEye.show();
    delay(100);
  }
}

void fullCircleWakeUp(int right) {
  //wake up animation with eyes open
  if (right == 0) {
    //open left eye completely
    for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
      leftEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
      leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
      leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0); // 2nd quarter ->
      leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0); // 3rd quarter <-
      leftEye.show();
      delay(100);
    }
  }
  else {
    if (right == 1) {
      //open right eye completely
      for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
        rightEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
        rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0); // 2nd quarter ->
        rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0); // 3rd quarter <-
        rightEye.show();
        delay(100);
      }
    }
    else {
      //open both eyes completely
      for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
        leftEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
        leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0); // 2nd quarter ->
        leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0); // 3rd quarter <-
        leftEye.show();
        rightEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
        rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0); // 2nd quarter ->
        rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0); // 3rd quarter <-
        rightEye.show();
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
      leftEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
      leftEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
      leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0); // 2nd quarter ->
      leftEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0); // 3rd quarter <-
      leftEye.show();
      rightEye.setPixelColor(i, 0, 255, 0); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
      rightEye.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0); // 4th quarter <-
      rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, 0, 255, 0); // 2nd quarter ->
      rightEye.setPixelColor(NUMPIXELS / 2 + i, 0, 255, 0); // 3rd quarter <-
      rightEye.show();
      delay(50);
    }
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
      delay(50);
    }
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
    delay(50);
  }
}

