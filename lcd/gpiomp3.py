import os
import subprocess
from time import sleep, strftime
import RPi.GPIO as GPIO
import alsaaudio
import Adafruit_CharLCD as LCD

def get_files(root):
	files = []
	
	def scan_dir(dir):
		for f in os.listdir(dir):
			f = os.path.join(dir, f)
			if os.path.isdir(f):
				scan_dir(f)
			elif os.path.splitext(f)[1] == ".mp3":
				files.append(f)
	scan_dir(root)
	return files

def printtitle(string):
	info = string.split('/')
	title = info[len(info) - 1]
	return title


buffer = get_files("/mnt/Music")
listlength = len(buffer)
print "listlength: %d" % listlength
print buffer	

#BUTTON DECLARATIONS
b_onoff = 22
b_playpause = 17
b_previous = 18
b_next = 27
b_voldown = 23
b_volup = 24

GPIO.setmode(GPIO.BCM)
GPIO.setup(b_onoff, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_playpause, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_previous, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_next, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_voldown, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_volup, GPIO.IN, pull_up_down=GPIO.PUD_UP)

lcd_rs = 20
lcd_en = 5
lcd_d4 = 6
lcd_d5 = 13
lcd_d6 = 19
lcd_d7 = 26
lcd_backlight = 21

lcd_columns = 16
lcd_rows = 2

lcd = LCD.Adafruit_CharLCD(lcd_rs, lcd_en, lcd_d4, lcd_d5, lcd_d6, lcd_d7, lcd_columns, lcd_rows, lcd_backlight)

song = 0
enterwhile = 0
playing = 0
m = alsaaudio.Mixer('PCM')
m.setvolume(82)
vol = m.getvolume()
vol = int(vol[0])
print "volume: %d\n" % vol
#FUNCTION DECLARATIONS

def volumelevels(realvol):
	if realvol == 0:
		return 0
	if realvol == 70:
		return 1
	if realvol == 73:
		return 2
	if realvol == 76:
		return 3
	if realvol == 79:
		return 4
	if realvol == 82:
		return 5
	if realvol == 85:
		return 6
	if realvol == 88:
		return 7
	if realvol == 91:
		return 8
	if realvol == 94:
		return 9
	if realvol == 97:
		return 10

def printmp3(printstring):
        timeinfo = strftime("%H:%M")
	volscale = volumelevels(vol)
        volumeinfo = "\nvolume:%d" % volscale
	if vol != 97:
        	secondline = volumeinfo + '   ' + timeinfo
	else:
		secondline = volumeinfo + '  ' + timeinfo
        if len(printstring) <= lcd_columns:
		lcd.clear()
                lcd.message(printstring)
                lcd.message(secondline)
        else:
                movements = len(printstring) - lcd_columns + 1
        	for i in range(0, movements):
			timeinfo = strftime("%H:%M")
			volscale = volumelevels(vol)
        		volumeinfo = "\nvolume:%d" % volscale
			if vol != 97:
       	 			secondline = volumeinfo + '   ' + timeinfo
			else:
				secondline = volumeinfo + '  ' + timeinfo

                	lcd.clear()
                	printnow = printstring[i:(i + lcd_columns)]
                	lcd.message(printnow)
                	lcd.message(secondline)
                	sleep(.5)

def switch_while(channel):
	global enterwhile
	if enterwhile == 0:
		enterwhile = 1
	else:
		enterwhile = 0

def pause(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		p.stdin.write('p')
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
	
GPIO.add_event_detect(b_onoff, GPIO.FALLING, callback=switch_while, bouncetime=300)
GPIO.add_event_detect(b_playpause, GPIO.FALLING, callback=pause, bouncetime=300)
GPIO.add_event_detect(b_previous, GPIO.FALLING, callback=previoussong, bouncetime=300)
GPIO.add_event_detect(b_next, GPIO.FALLING, callback=nextsong, bouncetime=300)
GPIO.add_event_detect(b_voldown, GPIO.FALLING, callback=volumedown, bouncetime=300)
GPIO.add_event_detect(b_volup, GPIO.FALLING, callback=volumeup, bouncetime=300)
while True:
	print playing
	if enterwhile == 1:
		lcd.set_backlight(0)
		printtitle(buffer[song])
		print "entered while"
		if playing == 0:
			p = subprocess.Popen(["mplayer", buffer[song]], stdin=subprocess.PIPE)
			songtitle = printtitle(buffer[song])
		print p.poll()
		if p.poll() != 0:
			print "ppoll None"
			playing = 1
		else:
			print "on else"
			playing = 0
			if song == listlength - 1:
				song = 0
			else:
				song = song + 1
		printmp3(songtitle)
	else:
		lcd.set_backlight(1)
		print "did not enter while"
		if 'p' in vars() or 'p' in globals():
			if p.poll() != 0:
				p.stdin.write('q')
				playing = 0
				del p
	#printmp3(songtitle)
	sleep(1)
GPIO.cleanup()
