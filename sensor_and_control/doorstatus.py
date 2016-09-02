from nanpy import (ArduinoApi, SerialManager)

magnet = 4

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except:
	print("Unable to connect to Arduino")
a.pinMode(magnet, a.INPUT)
if(a.digitalRead(magnet)):
	print("Door is closed")
else:
	print("Door is open")
