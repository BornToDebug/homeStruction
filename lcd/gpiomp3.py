#!/usr/bin/python
import os
import subprocess
from time import sleep, strftime
import RPi.GPIO as GPIO
import alsaaudio
import Adafruit_CharLCD as LCD

#Button constants declaration using the BCM GPIO numbering
b_onoff = 22
b_playpause = 17
b_previous = 18
b_next = 27
b_voldown = 23
b_volup = 24

#Setting up the GPIO ports
GPIO.setmode(GPIO.BCM)
GPIO.setup(b_onoff, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_playpause, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_previous, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_next, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_voldown, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_volup, GPIO.IN, pull_up_down=GPIO.PUD_UP)

#LCD pins setup using BCM GPIO numbering
lcd_rs = 20
lcd_en = 5
lcd_d4 = 6
lcd_d5 = 13
lcd_d6 = 19
lcd_d7 = 26
lcd_backlight = 21

lcd_columns = 16
lcd_rows = 2

#Initializing LCD
lcd = LCD.Adafruit_CharLCD(lcd_rs, lcd_en, lcd_d4, lcd_d5, lcd_d6, lcd_d7, lcd_columns, lcd_rows, lcd_backlight)

#Variables needed:
#Song counter, range = 0 to listenlength
song = 0
#The variable that enables the music player and the lcd display
enterwhile = 0
#If nothing is playing(playing = 0) and the player is enabled, a process will be started, then playing=1
playing = 0
#Audio instance for the Raspberry Pi volume control
m = alsaaudio.Mixer('PCM')
#Setting default volume to 82
m.setvolume(82)
vol = m.getvolume()
vol = int(vol[0])
print "volume: %d\n" % vol
#FUNCTION DECLARATIONS

#Function that scans .mp3 files in a directory(the root directory given, in this case /mnt/Music)
def get_files(root):
	files = []
	
	def scan_dir(dir):
		for f in os.listdir(dir):
			#Creates path string
			f = os.path.join(dir, f)
			#If the path is another directory, the function calls itself to scan that directory as well
			if os.path.isdir(f):
				scan_dir(f)
			#else it adds the path to the file list
			elif os.path.splitext(f)[1] == ".mp3":
				files.append(f)
	scan_dir(root)
	return files

#Returns only the song title from the path string
def printtitle(string):
	info = string.split('/')
	title = info[len(info) - 1]
	return title


buffer = get_files("/mnt/Stick/Music")

#The length of the list is the length of buffer 
listlength = len(buffer)
print "listlength: %d" % listlength
print buffer	


#Realvolume:scale dictionary
volumelevel = {0:0, 70:1, 73:2, 76:3, 79:4, 82:5, 85:6, 88:7, 91:8, 94:9, 97:10}

#Creates a string for the second line containing the current time and the volume. 
def createSecondLine():
	#Getting the hour and minutes only, local time
	timeinfo = strftime("%H:%M")
	#Converts the real volume into the volume scale
	volscale = volumelevel[vol]
        volumeinfo = "\nvolume:%d" % volscale
	#If the real volume is 97, the volume scale is 10, so we need one more character on the lcd display for volume
	if vol != 97:
        	secondline = volumeinfo + '   ' + timeinfo
	else:
		secondline = volumeinfo + '  ' + timeinfo
	return secondline

#Controls the lcd display, currently not used because interrupts cannot exit for loops
def printmp3(printstring):
	secondline = createSecondLine()
        if len(printstring) <= lcd_columns:
		lcd.clear()
                lcd.message(printstring)
                lcd.message(secondline)
        else:
                movements = len(printstring) - lcd_columns + 1
        	for i in range(0, movements):
			secondline = createSecondLine()
                	lcd.clear()
                	printnow = printstring[i:(i + lcd_columns)]
                	lcd.message(printnow)
                	lcd.message(secondline)
                	sleep(.5)

#Callback funtions:

#Switches the player on
def switch_while(channel):
	global enterwhile
	if enterwhile == 0:
		enterwhile = 1
	else:
		enterwhile = 0

#Pauses playback
def pause(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		p.stdin.write('p')

#Previous track; quits current playback, updates the song variable and sets playing to 0, so that
#when the program enters the while loop, it will automatically start a process for the playback
def previoussong(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		global song
		global playing
		p.stdin.write('q')
		del p
		if song == 0:
			song = listlength - 1
		else:
			song = song - 1
		playing = 0

#Similar to previoussong
def nextsong(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		global song
		global playing
		p.stdin.write('q')
		del p
		if song == listlength - 1:
			song = 0
		else:
			song = song + 1
		playing = 0

#Volume up; 
#if the volume is 0(muted) and we want to unmute, it sets the real volume to 70
#else, if it is smaller than the max volume, it adds 3
def volumeup(channel):
	global vol
	vol = m.getvolume()
	vol = int(vol[0])
	if vol == 0:
		m.setvolume(70)
		vol = 70
		print "volume: 70\n"
	else:
		if vol < 97:
			vol = vol + 3
			m.setvolume(vol)
			print "volume: %d\n" % vol
		else:
			print "volume: MAX\n"

#Volume down;
#if the volume is greater than 70, it subtracts to make it smaller
#else, if the volume is == 70 and we still want to decrease it, it will mute the volume
def volumedown(channel):
	global vol
	vol = m.getvolume()
	vol = int(vol[0])
	if vol > 70:
		vol = vol - 3
		m.setvolume(vol)
		print "volume: %d\n" % vol
	else:
		if vol != 0:
			m.setvolume(0)
			vol = 0
		print "volume: MUTED\n"
	
#GPIO callback declarations
GPIO.add_event_detect(b_onoff, GPIO.FALLING, callback=switch_while, bouncetime=500)
GPIO.add_event_detect(b_playpause, GPIO.FALLING, callback=pause, bouncetime=500)
GPIO.add_event_detect(b_previous, GPIO.FALLING, callback=previoussong, bouncetime=500)
GPIO.add_event_detect(b_next, GPIO.FALLING, callback=nextsong, bouncetime=500)
GPIO.add_event_detect(b_voldown, GPIO.FALLING, callback=volumedown, bouncetime=500)
GPIO.add_event_detect(b_volup, GPIO.FALLING, callback=volumeup, bouncetime=500)

#Main loop, requires only about 4% CPU usage because it sleeps 1s after every iteration
while True:
	print playing
	if enterwhile == 1:
		#Display backlight is 0 (ON!!!) when the player is working
		lcd.set_backlight(0)
		printtitle(buffer[song])
		print "entered while"
		if playing == 0:
			#Opening subprocess
			p = subprocess.Popen(["mplayer", buffer[song]], stdin=subprocess.PIPE)
			#Getting the title of the new song
			printstring = printtitle(buffer[song])
			#Decides whether the title is longer than the display size, because then it has to shift left
			if len(printstring) <= lcd_columns:
				movements = 0
			else:
				movements = len(printstring) - lcd_columns + 1
				i = 0
		print p.poll()
		#p.poll() tells if the process terminated or it is still running
		#if it is still running
		if p.poll() != 0:
			print "ppoll None"
			playing = 1
		#if it has terminated, we need to start the new song automatically
		else:
			print "on else"
			playing = 0
			#if we are at the end of the list, start from the beginning, else continue
			if song == listlength - 1:
				song = 0
			else:
				song = song + 1
		#Controlling the display
		secondline = createSecondLine()
		if movements == 0:
			lcd.clear()
			lcd.message(printstring)
			lcd.message(secondline)
		else:
			if i == movements:
				i = 0
			lcd.clear()
			printnow = printstring[i:(i+lcd_columns)]
			lcd.message(printnow)
			lcd.message(secondline)
			i = i+1
		sleep(1)
	#If the player is not working
	else:
		#The display should be 1 (OFF!!)
		lcd.set_backlight(1)
		print "did not enter while"
		#Close the running process if there is such process
		if 'p' in vars() or 'p' in globals():
			if p.poll() != 0:
				p.stdin.write('q')
				playing = 0
				del p
		sleep(1)
GPIO.cleanup()
