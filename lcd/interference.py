import RPi.GPIO as GPIO
from time import sleep, strftime

GPIO.setmode(GPIO.BCM)
GPIO.setup(22, GPIO.IN)
GPIO.setup(24, GPIO.IN)

def first(channel):
	print strftime('%H:%M:%S'), 'first pressed'
def second(channel):
	print strftime('%H:%M:%S'), 'second pressed'

GPIO.add_event_detect(22, GPIO.FALLING, callback=first, bouncetime=300)
GPIO.add_event_detect(24, GPIO.FALLING, callback=second, bouncetime=300)

try:
	sleep(20)
except KeyboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
