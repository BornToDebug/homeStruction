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


from project.models import Temperature, Light, Humidity

def between(what, a, b):
	if what > a and what < b:
		return True
	return False


#Initialize serial connection
ser = serial.Serial('/dev/ttyACM0', 9600);

#Send request to update the data
ser.write('3');
#Wait for it to be generated
sleep(3)
#Read the big chunk of data
bigchunk = ser.readline()
sleep(1)
#DEBUG:
print bigchunk
#Process the data
words = bigchunk.split()
temperature = float(words[0])
light = float(words[1])
humidity = float(words[6])

if between(temperature, 10.0, 100.0) and between(light, 10.0, 1050.0) and between(humidity, 10.0, 100.0):
	Temperature.objects.create(value=temperature, time_recorded=timezone.now())
	Light.objects.create(value=light, time_recorded=timezone.now())
	Humidity.objects.create(value=humidity, time_recorded=timezone.now())
ser.close()

