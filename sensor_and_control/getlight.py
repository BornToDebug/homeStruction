from nanpy import (ArduinoApi, SerialManager)
from time import sleep

sensor = 18

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except:
	print("Could not connect to Arduino")
lightavg = 0
for i in range(0, 10):
	light = a.analogRead(sensor)
#	print("%d: %d" % (i, light))
	lightavg += light
#	sleep(.2) 

lightavg = lightavg / 10.0
print("%f" %lightavg)
