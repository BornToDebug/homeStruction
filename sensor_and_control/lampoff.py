from nanpy import (ArduinoApi, SerialManager)

Pin = 2

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except:
	print("Unable to connect to Arduino")

a.pinMode(Pin, a.OUTPUT)
a.digitalWrite(Pin, a.LOW)
