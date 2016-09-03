import django
import os
from django.utils import timezone
from nanpy import (ArduinoApi, SerialManager)

# Initial setup required
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()

from project.models import Temperature
sensor = 19
powervoltage = 5

try:
    connection = SerialManager()
    a = ArduinoApi(connection=connection)
except:
    print("Could not connect to Arduino")

tempavg = 0
for i in range(0, 10):
    sensorValue = a.analogRead(sensor)
    temperature = (sensorValue / 1023) * powervoltage * 100
    # print("%d: %2.2f" % (i, temperature))
    tempavg += temperature

# sleep(.2)
tempavg /= 10.0
Temperature.objects.create(value=tempavg, time_recorded=timezone.now())
