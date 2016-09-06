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

#Initialize serial connection
ser = serial.Serial('/dev/ttyACM0', 9600);

#Send request to update the data
ser.write('3');
#Wait for it to be generated
sleep(2)
#Read the big chunk of data
bigchunk = ser.readline()
#sleep(1)
#DEBUG: 
print bigchunk
#Process the data
words = bigchunk.split()
temperature = float(words[0])
light = int(words[1])
#lampstatus = int(words[2])
#doorstatus = int(words[3])
#windowstatus = int(words[4])
Temperature.objects.create(value=temperature, time_recorded=timezone.now())
ser.close()

