#include <Adafruit_NeoPixel.h>

#define RIGHT 6
#define NUMPIXELS 16
#define MIDDLE 8

Adafruit_NeoPixel pixels = Adafruit_NeoPixel(NUMPIXELS, RIGHT, NEO_GRB + NEO_KHZ800);

void setup() {
  // put your setup code here, to run once:
  pixels.setBrightness(1);
  pixels.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
  halfCircleWakeUp();
  reset();
  fullCircleWakeUp();
  reset();
  clip();
  reset();
}

void halfCircleWakeUp() {
  for (int i = 0; i < NUMPIXELS / 4; i++) {
    pixels.setPixelColor(MIDDLE + i, 0, 255, 0);
    pixels.setPixelColor(MIDDLE - i - 1, 0, 255, 0);
    pixels.show();
    delay(100);
  }
  delay(500);
}

void fullCircleWakeUp() {
  for (int i = 0; i < MIDDLE; i++) {
    pixels.setPixelColor(MIDDLE + i, 0, 255, 0);
    pixels.setPixelColor(MIDDLE - i - 1, 0, 255, 0);
    pixels.show();
    delay(100);
  }
  delay(500);
}

void reset() {
  for (int i = 0; i < NUMPIXELS; i++) {
    pixels.setPixelColor(i, 0, 0, 0);
    pixels.show();
  }
  delay(200);
}

void clip() {
  for (int j = 0; j < 3; j++) {
    for (int i = NUMPIXELS / 4 - 1; i >= 0; i--) {
      pixels.setPixelColor(i, 0, 255, 0);
      pixels.setPixelColor(NUMPIXELS - i - 1, 0, 255, 0);
      pixels.setPixelColor(MIDDLE - i - 1, 0, 255, 0);
      pixels.setPixelColor(MIDDLE + i, 0, 255, 0);
      pixels.show();
      delay(50);
    }
    delay(200);

    for (int i = 0; i < NUMPIXELS / 4; i++) {
      pixels.setPixelColor(i, 0, 0, 0);
      pixels.setPixelColor(NUMPIXELS - i - 1, 0, 0, 0);
      pixels.setPixelColor(MIDDLE - i - 1, 0, 0, 0);
      pixels.setPixelColor(MIDDLE + i, 0, 0, 0);
      pixels.show();
      delay(50);
    }
    delay(200);
  }
}

