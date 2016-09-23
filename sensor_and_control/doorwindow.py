import RPi.GPIO as GPIO
from time import strftime, sleep

doorpin = 25
windowpin = 12

GPIO.setmode(GPIO.BCM)
GPIO.setup(doorpin, GPIO.IN)
GPIO.setup(windowpin, GPIO.IN)

def door(channel):
	if GPIO.input(doorpin) == 1:
		sleep(0.3)
		if GPIO.input(doorpin) != 1:
			print strftime("%a, %d %b %Y %H:%M:%S:"), "SPIKE!!!"
			return
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "DOOR closed"
	else:
		sleep(0.3)
		if GPIO.input(doorpin) != 0:
			print strftime("%a, %d %b %Y %H:%M:%S:"), "SPIKE!!!"
			return
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "DOOR opened"

def window(channel):
	if GPIO.input(windowpin) == 1:
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "WINDOW closed"
	else:
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "WINDOW opened"

GPIO.add_event_detect(doorpin, GPIO.BOTH, callback=door, bouncetime=300)
GPIO.add_event_detect(windowpin, GPIO.BOTH, callback=window, bouncetime=300)


try:
	raw_input("Press a key...")
except KeyboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
