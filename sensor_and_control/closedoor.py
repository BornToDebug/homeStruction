import RPi.GPIO as GPIO
from time import sleep

GPIO.setmode(GPIO.BOARD)
GPIO.setup(11, GPIO.OUT)

pwm = GPIO.PWM(11, 50)

pwm.start(7.5)
sleep(.3)
pwm.stop()

GPIO.cleanup()
