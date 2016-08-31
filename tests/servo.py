from nanpy import Servo
import time

servo = Servo(3)
while(1):
	move = input("Where to move? ")
	servo.write(move)

