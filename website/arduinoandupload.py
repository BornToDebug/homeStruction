import datetime
import lirc
import sys
sys.path.insert(0, '/home/projekt/homeStruction/website')
import django
import os
from django.utils import timezone
from time import sleep
import serial

#Initial setup required
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()
from project.models import Temperature

#Initialize the infrared connection
sockid = lirc.init("myprogram")

#Initialize serial connection
ser = serial.Serial('/dev/ttyACM0', 9600)

########################################################################################
#Definition of the EXECUTE function, jump over it and come back later:
def execute(icode, itemperature, ilight, ilampstatus, idoorstatus, iwindowstatus):
	print icode
	#Decide what to do:
	if icode == ["one"]:
		#Turn on the lamp
		ser.write('1')
	if icode == ["two"]:
		#Turn off the lamp
		ser.write('2')
	if icode == ["three"]:
		#Print the status of the lamp
		print ilampstatus
	if icode == ["seven"]:
		#Print the temperature
		print itemperature
	if icode == ["eight"]:
		#Print light
		print ilampstatus
	if icode == ["door"]:
		#Print the door status
		print idoorstatus
	if icode == ["window"]:
		#Print the window status
		print iwindowstatus
########################################################################################
now = datetime.datetime.now()
print now.minute

#Initialize a value for the nextcode
print "> "
code = lirc.nextcode()
print code

#Initialize False for the magic variable
magic = False

#Main loop, works until we don't exit it
while code != ["exit"]:
	#Set executed to False
	executed = False
	#Read the big chunk of data
	bigchunk = ser.readline()
	sleep(1)
	#DEBUG: 
	print bigchunk
	#Process the data
	words = bigchunk.split()
	temperature = float(words[0])
	light = int(words[1])
	lampstatus = int(words[2])
	doorstatus = int(words[3])
	windowstatus = int(words[4])
	#If it is the 0th, 5th, 10th, 15th, ... , 50th, 55th minute 
	if now.minute in [0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 55]:
		if not(magic):
			#Upload the temperature to the database
			Temperature.objects.create(value=temperature, time_recorded=timezone.now())
			#Change the value of the magic variable to True
			magic = True
		else:
			#Even if we are in these minutes, but we have already updated the database, we must execute the nextcode
			execute(code, temperature, light, lampstatus, doorstatus, windowstatus)
			executed = True
	#Else , if we are not in those minutes, change the magic variable to False
	else:
		magic = False
	#Execute the command represented by the nextcode, if it wasn't already executed
	if not(executed):
		execute(code, temperature, light, lampstatus, doorstatus, windowstatus)

	#Prompt for nextcode
	print "> "
	code = lirc.nextcode()
	print code

#Deinitialize infrared connection
lirc.deinit()

#Close the serial connection
ser.close()
