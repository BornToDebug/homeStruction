import time
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BOARD)
red = 37


GPIO.setup(red, GPIO.OUT)

blink_num = input("How many times do you want to blink? ")

for i in range(0, blink_num):
	GPIO.output(red, True)
	time.sleep(1)
	GPIO.output(red, False)
	time.sleep(1)
GPIO.cleanup()
