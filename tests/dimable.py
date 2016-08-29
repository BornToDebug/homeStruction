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

frequency = 50
dutycycle = 50

pwm1 = GPIO.PWM(LED1, frequency)
pwm2 = GPIO.PWM(LED2, frequency)
pwm1.start(dutycycle)
pwm2.start(dutycycle)
while(1):
	if GPIO.input(button1) == 0:
		print "Button 1 was pressed"
		if dutycycle >= 10:
			dutycycle -= 10
			pwm1.ChangeDutyCycle(dutycycle)
			pwm2.ChangeDutyCycle(dutycycle)
			sleep(.5)
	if GPIO.input(button2) == 0:
		print "Button 2 was pressed"
		if dutycycle <= 90:
			dutycycle += 10
			pwm1.ChangeDutyCycle(dutycycle)
			pwm2.ChangeDutyCycle(dutycycle)
			sleep(.5)

