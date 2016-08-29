from time import sleep
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BOARD)
button1 = 16
button2 = 12
LED1 = 11
LED2 = 13
GPIO.setup(button1, GPIO.IN, pull_up_down = GPIO.PUD_UP)
GPIO.setup(button2, GPIO.IN, pull_up_down = GPIO.PUD_UP)
GPIO.setup(LED1, GPIO.OUT)
GPIO.setup(LED2, GPIO.OUT)
BS1 = False
BS2 = False
while(1):
	if GPIO.input(button1) == 0:
		print "Button 1 was Pressed"
		if BS1 == False:
			GPIO.output(LED1, 1)
			BS1 = True
			sleep(.5)
		else:
			GPIO.output(LED1, 0)
			BS1 = False
			sleep(.5)
	if GPIO.input(button2) == 0:
		print "Button 2 was Pressed"
		if BS2 == False:
			GPIO.output(LED2, 1)
			BS2 = True
			sleep(.5)
		else:
			GPIO.output(LED2, 0)
			BS2 = False
			sleep(.5)
