import RPi.GPIO as GPIO
from time import strftime, sleep

doorpin = 25
windowpin = 12

GPIO.setmode(GPIO.BCM)
GPIO.setup(doorpin, GPIO.IN)
GPIO.setup(windowpin, GPIO.IN)

doorstatus = None
windowstatus = None

def door(channel):
	global doorstatus
	if GPIO.input(doorpin) == 1:
		if doorstatus == None:
			doorstatus = 'closed'
		elif doorstatus == 'opened':
			doorstatus = 'closed'
			#UPDATE DATABASE
			print strftime("%a, %d %b %Y %H:%M:%S:"), "DOOR closed"
	else:
		if doorstatus == None:
			doorstatus = 'opened'
		elif doorstatus == 'closed':
			doorstatus = 'opened'
			#UPDATE DATABASE
			print strftime("%a, %d %b %Y %H:%M:%S:"), "DOOR opened"

def window(channel):
	global windowstatus
	if GPIO.input(windowpin) == 1:
		if windowstatus == None:
			windowstatus = 'closed'
		elif windowstatus == 'opened':
			windowstatus = 'closed'
			#UPDATE DATABASE
			print strftime("%a, %d %b %Y %H:%M:%S:"), "WINDOW closed"
	else:
		if windowstatus == None:
			windowstatus = 'opened'
		elif windowstatus == 'closed':
			windowstatus = 'opened'
			#UPDATE DATABASE
			print strftime("%a, %d %b %Y %H:%M:%S:"), "WINDOW opened"

GPIO.add_event_detect(doorpin, GPIO.BOTH, callback=door, bouncetime=300)
GPIO.add_event_detect(windowpin, GPIO.BOTH, callback=window, bouncetime=300)


try:
	raw_input("Press a key...")
except KeyboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
