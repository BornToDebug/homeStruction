import sys
sys.path.insert(0, '/home/projekt/homeStruction/website')
import serial
from time import sleep
import re
import os
import django
from django.utils import timezone

# Initial setup required
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()


from project.models import Lamp

script, todo = sys.argv

def confirmation(todo, ser):
	MyInt = -1
	sleep(2)
	ser.write('3');
	sleep(1)
	bigchunk = ser.readline()
	sleep(.5)
	words = bigchunk.split()
	if todo == '1lampon':
		MyInt = int(words[3])
		if MyInt == 1:
			return True
	if todo == '1lampoff':
		MyInt = int(words[3])
		if MyInt == 0:
			return True
	if todo == '2lampon':
		MyInt = int(words[2])
		if MyInt == 1:
			return True
	if todo == '2lampoff':
		MyInt = int(words[2])
		if MyInt == 0:
			return True
	if todo == '3lampon':
		MyInt = int(words[4])
		if MyInt == 1:
			return True
	if todo == '3lampoff':
		MyInt = int(words[4])
		if MyInt == 0:
			return True
	if todo == 'opendoor':
		MyInt = int(words[5])
		if MyInt == 72:
			return True
	if todo == 'closedoor':
		MyInt = int(words[5])
		if MyInt == 10:
			return True
	return False


try:	
	ser = serial.Serial('/dev/ttyACM0', 9600)
	if todo == '1lampon':
		ser.write('7')
		if confirmation(todo, ser):
			Lamp.objects.create(value='1on_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='1on_uc', time_recorded=timezone.now())

	if todo == '1lampoff':
		ser.write('8')
		if confirmation(todo, ser):
			Lamp.objects.create(value='1off_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='1off_uc', time_recorded=timezone.now())

	if todo == '2lampon':
		ser.write('1')
		if confirmation(todo, ser):
			Lamp.objects.create(value='2off_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='2off_uc', time_recorded=timezone.now())

	if todo == '2lampoff':
		ser.write('2')
		if confirmation(todo, ser):
			Lamp.objects.create(value='2on_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='2on_uc', time_recorded=timezone.now())

	if todo == '3lampon':
		ser.write('6')
		if confirmation(todo, ser):
			Lamp.objects.create(value='3off_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='3off_uc', time_recorded=timezone.now())

	if todo == '3lampoff':
		ser.write('9')
		if confirmation(todo, ser):
			Lamp.objects.create(value='3on_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='3on_uc', time_recorded=timezone.now())

	if todo == 'opendoor':
		ser.write('5')
		if confirmation(todo, ser):
			Lamp.objects.create(value='do_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='do_uc', time_recorded=timezone.now())

	if todo == 'closedoor':
		ser.write('4')
		if confirmation(todo, ser):
			Lamp.objects.create(value='dc_c', time_recorded=timezone.now())
		else:
			Lamp.objects.create(value='dc_uc', time_recorded=timezone.now())
	
	ser.close()
	
except serial.SerialException:
	Lamp.objects.create(value="ConnError", time_recorded=timezone.now())

