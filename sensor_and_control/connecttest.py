from nanpy import (ArduinoApi, SerialManager)
from time import sleep

ledPin = 13

try:
	connection = SerialManager()
	a = ArduinoApi(connection = connection)
except:
	print("Unable to connect to Arduino")

a.pinMode(ledPin, a.OUTPUT)

a.digitalWrite(ledPin, a.HIGH)
sleep(1)
a.digitalWrite(ledPin, a.LOW)
sleep(1)
a.digitalWrite(ledPin, a.HIGH)
sleep(1)
a.digitalWrite(ledPin, a.LOW)

