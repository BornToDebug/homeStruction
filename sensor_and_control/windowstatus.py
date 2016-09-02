from nanpy import (ArduinoApi, SerialManager)

magnet = 7

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except:
	print("Unable to connect to Arduino")
a.pinMode(magnet, a.INPUT)
if(a.digitalRead(magnet)):
	print("Window is closed")
else:
	print("Window is open")
