import time
import RPi.GPIO as GPIO

def on(PIN):
	GPIO.output(PIN, 1)
def off(PIN):
	GPIO.output(PIN, 0)

GPIO.setmode(GPIO.BOARD)
LSB=11
MID=13
MSB=15
GPIO.setup(LSB, GPIO.OUT)
GPIO.setup(MID, GPIO.OUT)
GPIO.setup(MSB, GPIO.OUT)

#000
off(LSB)
off(MID)
off(MSB)
time.sleep(1)

#001
on(LSB)
off(MID)
off(MSB)
time.sleep(1)

#010
off(LSB)
on(MID)
off(MSB)
time.sleep(1)

#011
on(LSB)
on(MID)
off(MSB)
time.sleep(1)

#100
off(LSB)
off(MID)
on(MSB)
time.sleep(1)

#101
on(LSB)
off(MID)
on(MSB)
time.sleep(1)

#110
off(LSB)
on(MID)
on(MSB)
time.sleep(1)

#111
on(LSB)
on(MID)
on(MSB)
time.sleep(1)

GPIO.cleanup()
