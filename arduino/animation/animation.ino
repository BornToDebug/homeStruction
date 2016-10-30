#include <Adafruit_NeoPixel.h>

#define RIGHT 6 //pin for the right eye
#define LEFT 10 //pin for the left eye
#define NUMPIXELS 16 //nr of pixels

Adafruit_NeoPixel rightEye = Adafruit_NeoPixel(NUMPIXELS, RIGHT, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel leftEye = Adafruit_NeoPixel(NUMPIXELS, LEFT, NEO_GRB + NEO_KHZ800);

uint32_t bluel = leftEye.Color(0, 0, 255);
uint32_t bluer = rightEye.Color(0, 0, 255);
uint32_t teall = leftEye.Color(0, 255, 255);
uint32_t tealr = rightEye.Color(0, 255, 255);
uint32_t greenl = leftEye.Color(0, 255, 0);
uint32_t greenr = rightEye.Color(0, 255, 0);
uint32_t yellowl = leftEye.Color(197, 244, 66);
uint32_t yellowr = rightEye.Color(197, 244, 66);

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
    choose(incoming);
  }
}

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
      //close both eyes
      sleepForever();
      break;
    case 9:
      //rotate colors
      rotate();
      break;
    case 10:
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
        leftEye.setPixelColor(NUMPIXELS / 2 + i, bluel);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluel);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, bluer);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluer);
        rightEye.show();
        break;
      case 1:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, teall);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, teall);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, tealr);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, tealr);
        rightEye.show();
        break;
      case 2:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, greenl);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenl);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, greenr);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenr);
        rightEye.show();
        break;
      case 3:
        leftEye.setPixelColor(NUMPIXELS / 2 + i, yellowl);
        leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowl);
        leftEye.show();
        rightEye.setPixelColor(NUMPIXELS / 2 + i, yellowr);
        rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowr);
        rightEye.show();
        break;
      default:
        break;
    }
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
          leftEye.setPixelColor(NUMPIXELS / 2 + i, bluel);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluel);
          leftEye.setPixelColor(i, bluel); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, bluel); // 4th quarter <-
          leftEye.show();
          break;
        case 1:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, teall);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, teall);
          leftEye.setPixelColor(i, teall); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, teall); // 4th quarter <-
          leftEye.show();
          break;
        case 2:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, greenl);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenl);
          leftEye.setPixelColor(i, greenl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, greenl); // 4th quarter <-
          leftEye.show();
          break;
        case 3:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, yellowl);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowl);
          leftEye.setPixelColor(i, yellowl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, yellowl); // 4th quarter <-
          leftEye.show();
          break;
        default:
          break;
      }
      delay(100);
    }
  }
  else {
    if (right == 1) {
      //open right eye completely
      for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
        switch (i) {
          case 0:
            rightEye.setPixelColor(i, bluer); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, bluer); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, bluer);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluer);
            rightEye.show();
            break;
          case 1:
            rightEye.setPixelColor(i, tealr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, tealr); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, tealr);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, tealr);
            rightEye.show();
            break;
          case 2:
            rightEye.setPixelColor(i, greenr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, greenr); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, greenr);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenr);
            rightEye.show();
            break;
          case 3:
            rightEye.setPixelColor(i, yellowr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, yellowr); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, yellowr);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowr);
            rightEye.show();
            break;
          default:
            break;
        }
        delay(100);
      }
    }
    else {
      //open both eyes completely
      for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
        switch (i) {
          case 0:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, bluel);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluel);
            leftEye.setPixelColor(i, bluel); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, bluel); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, bluer); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, bluer); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, bluer);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluer);
            rightEye.show();
            break;
          case 1:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, teall);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, teall);
            leftEye.setPixelColor(i, teall); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, teall); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, tealr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, tealr); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, tealr);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, tealr);
            rightEye.show();
            break;
          case 2:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, greenl);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenl);
            leftEye.setPixelColor(i, greenl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, greenl); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, greenr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, greenr); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, greenr);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenr);
            rightEye.show();
            break;
          case 3:
            leftEye.setPixelColor(NUMPIXELS / 2 + i, yellowl);
            leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowl);
            leftEye.setPixelColor(i, yellowl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            leftEye.setPixelColor(NUMPIXELS - i - 1, yellowl); // 4th quarter <-
            leftEye.show();
            rightEye.setPixelColor(i, yellowr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
            rightEye.setPixelColor(NUMPIXELS - i - 1, yellowr); // 4th quarter <-
            rightEye.setPixelColor(NUMPIXELS / 2 + i, yellowr);
            rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowr);
            rightEye.show();
            break;
          default:
            break;
        }
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
          leftEye.setPixelColor(NUMPIXELS / 2 + i, bluel);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluel);
          leftEye.setPixelColor(i, bluel); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, bluel); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, bluer); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, bluer); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, bluer);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, bluer);
          rightEye.show();
          break;
        case 1:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, teall);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, teall);
          leftEye.setPixelColor(i, teall); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, teall); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, tealr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, tealr); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, tealr);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, tealr);
          rightEye.show();
          break;
        case 2:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, greenl);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenl);
          leftEye.setPixelColor(i, greenl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, greenl); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, greenr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, greenr); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, greenr);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, greenr);
          rightEye.show();
          break;
        case 3:
          leftEye.setPixelColor(NUMPIXELS / 2 + i, yellowl);
          leftEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowl);
          leftEye.setPixelColor(i, yellowl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          leftEye.setPixelColor(NUMPIXELS - i - 1, yellowl); // 4th quarter <-
          leftEye.show();
          rightEye.setPixelColor(i, yellowr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          rightEye.setPixelColor(NUMPIXELS - i - 1, yellowr); // 4th quarter <-
          rightEye.setPixelColor(NUMPIXELS / 2 + i, yellowr);
          rightEye.setPixelColor(NUMPIXELS / 2 - i - 1, yellowr);
          rightEye.show();
          break;
        default:
          break;
      }
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


void rotate() {
  //rotate colors (one cycle)
  for (int i = 1; i <= NUMPIXELS; i++) {
    Serial.print("i = ");
    Serial.println(i);
    for (int k = NUMPIXELS / 4 - 1; k >= 0 ; k--) {
      int j;
      if (i >= NUMPIXELS / 2){
        j = i - NUMPIXELS / 2;
      }
      else {
        j = i;
      }
      Serial.print("j = ");
      Serial.println(j);
      Serial.print("k = ");
      Serial.println(k);
      switch (k) {
        case 0:
        //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, bluel);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, bluel);
          }
          //2nd quarter
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, bluel); // 3rd quarter
          leftEye.setPixelColor(k + j, bluel); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            leftEye.setPixelColor(- k - 1 + j, bluel); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, bluel); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, bluer);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, bluer);
          }
          //2nd quarter
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, bluer); // 3rd quarter
          rightEye.setPixelColor(k + j, bluer); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            rightEye.setPixelColor(- k - 1 + j, bluer); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, bluer); // 4th quarter <-
          }
          rightEye.show();
          break;
        case 1:
        //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, teall);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, teall);
          }
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, teall);
          leftEye.setPixelColor(k + j, teall); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            leftEye.setPixelColor(- k - 1 + j , teall); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, teall); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, tealr);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, tealr);
          }
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, tealr);
          rightEye.setPixelColor(k + j, tealr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            rightEye.setPixelColor(- k - 1 + j , tealr); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, tealr); // 4th quarter <-
          }
          rightEye.show();
          break;
        case 2:
        //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, greenl);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, greenl);
          }
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, greenl);
          leftEye.setPixelColor(k + j, greenl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            leftEye.setPixelColor(- k - 1 + j, greenl); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, greenl); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, greenr);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, greenr);
          }
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, greenr);
          rightEye.setPixelColor(k + j, greenr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            rightEye.setPixelColor(- k - 1 + j, greenr); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, greenr); // 4th quarter <-
          }
          rightEye.show();
          break;
        case 3:
        //left eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, yellowl);
          }
          else {
            leftEye.setPixelColor(NUMPIXELS / 2 + k + j, yellowl);
          }
          leftEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, yellowl);
          leftEye.setPixelColor(k + j, yellowl); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            leftEye.setPixelColor(- k - 1 + j, yellowl); // 4th quarter <-
          }
          else {
            leftEye.setPixelColor(NUMPIXELS - k - 1 + j, yellowl); // 4th quarter <-
          }
          leftEye.show();

          //right eye rotate
          if (NUMPIXELS / 2 + k + j >= NUMPIXELS){
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j - NUMPIXELS, yellowr);
          }
          else {
            rightEye.setPixelColor(NUMPIXELS / 2 + k + j, yellowr);
          }
          rightEye.setPixelColor(NUMPIXELS / 2 - k - 1 + j, yellowr);
          rightEye.setPixelColor(k + j, yellowr); // if 0 is the upper pixel, first quarter (as on the trigonometrical circle) ->
          if (NUMPIXELS - k - 1 + j >= NUMPIXELS){
            rightEye.setPixelColor(- k - 1 + j, yellowr); // 4th quarter <-
          }
          else {
            rightEye.setPixelColor(NUMPIXELS - k - 1 + j, yellowr); // 4th quarter <-
          }
          rightEye.show();
          break;
        default:
          break;
      }
    }
    delay(100);
  }
}

