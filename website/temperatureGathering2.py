import sys
sys.path.insert(0, '/home/projekt/homeStruction/website')
import django
import os
from django.utils import timezone
from time import sleep
import serial

# Initial setup required
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()


from project.models import Temperature

#Reading the temperature
ser = serial.Serial('/dev/ttyACM0', 9600)
sleep(1)
ser.write('20')
sleep(4)
temperature = float(ser.readline())
print temperature
print timezone.now()
Temperature.objects.create(value=temperature, time_recorded=timezone.now())


