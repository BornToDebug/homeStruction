#include <Adafruit_NeoPixel.h>

#define RIGHT 6
#define NUMPIXELS 16

Adafruit_NeoPixel pixels = Adafruit_NeoPixel(NUMPIXELS, RIGHT, NEO_GRB + NEO_KHZ800);

void setup() {
  // put your setup code here, to run once:
  pixels.setBrightness(1);
  pixels.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
  for (int i = 0; i < NUMPIXELS; i++){
    pixels.setPixelColor(i, 66, 244, 209);
    pixels.show();
    delay(100);
  }
  for (int i = 0; i < NUMPIXELS; i++){
    pixels.setPixelColor(i, 0, 0, 0);
    pixels.show();
    delay(100);
  }
}
