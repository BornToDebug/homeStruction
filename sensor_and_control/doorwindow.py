import RPi.GPIO as GPIO
from time import strftime

door = 25
window = 12

GPIO.setmode(GPIO.BCM)
GPIO.setup(door, GPIO.IN)
GPIO.setup(window, GPIO.IN)

def door(channel):
	if GPIO.input(25) == 1:
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "DOOR closed"
	else:
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "DOOR opened"

def window(channel):
	if GPIO.input(12) == 1:
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "WINDOW closed"
	else:
		#UPDATE DATABASE
		print strftime("%a, %d %b %Y %H:%M:%S:"), "WINDOW opened"

GPIO.add_event_detect(25, GPIO.BOTH, callback=door, bouncetime=300)
GPIO.add_event_detect(12, GPIO.BOTH, callback=window, bouncetime=300)


try:
	raw_input("Press a key...")
except KeyboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
