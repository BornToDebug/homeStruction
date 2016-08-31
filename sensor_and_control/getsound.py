from nanpy import (ArduinoApi, SerialManager)
from time import sleep

sensor = 17

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except:
	print("Unable to connect to Arduino")

soundavg = 0
for i in range(0, 100):
	soundread = a.analogRead(17)
#	print("%d: %d" % (i, soundread))
	soundavg += soundread
#	sleep(.2)
soundavg = soundavg / 100.0
#sound = a.analogRead(sensor)

print("%f" %soundavg)
