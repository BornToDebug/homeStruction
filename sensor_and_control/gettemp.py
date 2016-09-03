from nanpy import (ArduinoApi, SerialManager)
from time import sleep

sensor = 19
powervoltage = 5

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except: 
	print("Could not connect to Arduino")

tempavg = 0
for i in range(0, 10):
	sensorValue = a.analogRead(sensor)
	temperature = (sensorValue/1023.0)*powervoltage*100
#	print("%d: %2.2f" % (i, temperature))
	tempavg += temperature
#	sleep(.2)
tempavg = tempavg / 10.0
print("%2.1f" % tempavg)
