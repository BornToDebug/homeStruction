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
#ser = serial.Serial('/dev/ttyACM0', 9600)

def portIsUsable():
	try:
		ser = serial.Serial('/dev/ttyACM0', 9600)
		return True
	except:
		return False

while not(portIsUsable()):
        sleep(4)
ser = serial.Serial('/dev/ttyACM0', 9600)

#Function to return datatype
def data(port):
	print "in data"
	ser.write(port)
	sleep(3)
	mystring = ser.readline()
	print mystring
	words = mystring.split()
	return words

words = data('20')
print words
#While something else is displayed in the serial, try again
while words[0] != 'temp':
	words = data('20')
	print words
print "got type"
temperature = float(words[1])
print "ready to print"
#Print data
print temperature
print timezone.now()
#Update database
Temperature.objects.create(value=temperature, time_recorded=timezone.now())

#Close serial communication
ser.close()
