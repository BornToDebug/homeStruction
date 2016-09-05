import sys
sys.path.insert(0, '/home/projekt/homeStruction/website')
import django
import os
from django.utils import timezone
from nanpy import (ArduinoApi, SerialManager)
from time import sleep
from subprocess import call

# Initial setup required
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()


from project.models import Temperature
sensor = 19
powervoltage = 5

connected = False
nrOfTries = 0
log = open('errors.log', 'a')
log.write('Trying to connect:' + str(timezone.now()))

while not connected and nrOfTries < 5:
    nrOfTries += 1
    try:
        connection = SerialManager()
        a = ArduinoApi(connection=connection)
        connected = True
    except:
        call(["/home/project/homeStruction/website/nanpyupdate"])
        log.write('Failed! Trying to reconnect' + str(nrOfTries))
        sleep(1)

if connected:
    tempavg = 0
    for i in range(0, 10):
        sensorValue = a.analogRead(sensor)
        temperature = (sensorValue / 1023.0) * powervoltage * 100
        # print("%d: %2.2f" % (i, temperature))
        tempavg += temperature

    # sleep(.2)
    tempavg /= 10.0
    print("%f" % tempavg)
    print timezone.now()
    Temperature.objects.create(value=tempavg, time_recorded=timezone.now())
else:
    log.write('could NOT connect! aborting...')
log.close()
